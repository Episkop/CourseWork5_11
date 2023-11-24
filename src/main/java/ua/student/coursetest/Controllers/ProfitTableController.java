package ua.student.coursetest.Controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ua.student.coursetest.Exception.AlreadyExistException;
import ua.student.coursetest.Exception.DBIsEmptyException;
import ua.student.coursetest.Exception.NotFoundException;
import ua.student.coursetest.Model.ProfitModel;
import ua.student.coursetest.Model.ProfitTotalModel;
import ua.student.coursetest.Model.SpendingModel;
import ua.student.coursetest.Model.SpendingTotalModel;
import ua.student.coursetest.Services.ProfitService;
import ua.student.coursetest.Services.SpendingService;

import java.util.List;
import java.util.Locale;

@Controller
@Slf4j
public class ProfitTableController {

    private final ProfitService profitService;

    private final SpendingService spendingService;
    public ProfitTableController(ProfitService profitService, SpendingService spendingService) {
        this.profitService = profitService;
        this.spendingService = spendingService;
    }

    @GetMapping("/profit")
    public String getAllProfit(Model model) {
        try {
            List<ProfitModel> listProfits = profitService.findAllProfit();
            model.addAttribute("listProfits", listProfits);
            List<ProfitTotalModel> listProfitTotal = profitService.getAllTotalProfit();
            model.addAttribute("listProfitTotal", listProfitTotal);
            List<SpendingModel> listSpending = spendingService.findAllSpending();
            model.addAttribute("listSpending", listSpending);
            List<SpendingTotalModel> listSpendingTotal = spendingService.getAllTotalSpending();
            model.addAttribute("listSpendingTotal", listSpendingTotal);
        } catch (DBIsEmptyException e) {
            throw new RuntimeException(e.getMessage());
        }
        return "index";
    }

    @GetMapping("/new")
    public String showNewForm(Model model) {
        model.addAttribute("profit", new ProfitModel());
        model.addAttribute("pageTitle", "Add New Profit Line");
        return "save_form";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute("profit") ProfitModel model, RedirectAttributes ra) {//добавил @modelAttribute и стало сохранять
        try {
            profitService.saveProfit(model);
            ra.addAttribute("message", "The new Line with article " + model.getArticle() + "has been saved successfully.");
        } catch (AlreadyExistException e) {
            throw new RuntimeException(e.getMessage());
        }
        return "redirect:/profit";
    }

//    @GetMapping("/edit/{article}")
//    public String showEditForm(@PathVariable("article") String article, Model model, RedirectAttributes ra) {
//        try {
//            ProfitModel profit = service.findProfit(article);
//            model.addAttribute("profit", profit);
//            model.addAttribute("pageTitle", "Edit Profit (" + article + ")");
//            return "save_form";
//        } catch (NotFoundException e) {
//            ra.addFlashAttribute("message", e.getMessage());
//            return "redirect:/profit";
//        }
//    }

    @GetMapping("/update/{article}")
    public String updateForm(@PathVariable("article") String article, Model model, RedirectAttributes ra) {
        try {
            model.addAttribute("profit", profitService.findProfit(article));
            model.addAttribute("pageTitle", "Add New Profit Line");
            return "edit_form";
        } catch (NotFoundException e) {
            ra.addFlashAttribute("message", e.getMessage());
            return "redirect:/profit";
        }
    }

    @PostMapping("/saveUpdate/{article}")
    public String update(@ModelAttribute("profit") ProfitModel profitModel,@PathVariable ("article") String article, RedirectAttributes ra) {
        try {
            profitService.updateProfit(profitModel);
            ra.addAttribute("message", "The new Line with article " + profitModel.getArticle() + "has been saved successfully.");
        } catch (NotFoundException e) {
            throw new RuntimeException(e);
        }
        return "redirect:/profit";
    }

    @GetMapping("/delete/{article}")
    public String deleteUser(@PathVariable("article") String article, RedirectAttributes ra) {
        try {
            profitService.delete(article);
            ra.addFlashAttribute("message", "The Attribute " + article.toUpperCase(Locale.ROOT) + " has been deleted.");
        } catch (NotFoundException e) {
            ra.addFlashAttribute("message", e.getMessage());
        }
        return "redirect:/profit";
    }
}
