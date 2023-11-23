package ua.student.coursetest.Controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ua.student.coursetest.Exception.AlreadyExistException;
import ua.student.coursetest.Exception.DBIsEmptyException;
import ua.student.coursetest.Exception.NotFoundException;
import ua.student.coursetest.Model.ProfitModel;
import ua.student.coursetest.Model.ProfitTotalModel;
import ua.student.coursetest.Services.ProfitService;

import java.util.List;
import java.util.Locale;

@Controller
@Slf4j
public class FirstTableController {

    private final ProfitService service;

    public FirstTableController(ProfitService service) {
        this.service = service;
    }

    @GetMapping("/profit")
    public String getAllProfit(Model model) {
        try {
            List<ProfitModel> listProfits = service.findAllProfit();
            model.addAttribute("listProfits", listProfits);
            List<ProfitTotalModel> listProfitTotal = service.getAllTotalProfit();
            model.addAttribute("listProfitTotal", listProfitTotal);
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
            service.saveProfit(model);
            ra.addAttribute("message", "The new Line with article " + model.getArticle() + "has been saved successfully.");
        } catch (AlreadyExistException e) {
            throw new RuntimeException(e.getMessage());
        }
        return "redirect:/profit";
    }

    @GetMapping("/edit/{article}")
    public String showEditForm(@PathVariable("article") String article, Model model, RedirectAttributes ra) {
        try {
            ProfitModel profit = service.findProfit(article);
            model.addAttribute("profit", profit);
            model.addAttribute("pageTitle", "Edit Profit (" + article + ")");
            return "save_form";
        } catch (NotFoundException e) {
            ra.addFlashAttribute("message", e.getMessage());
            return "redirect:/profit";
        }
    }

    @GetMapping("/delete/{article}")
    public String deleteUser(@PathVariable("article") String article, RedirectAttributes ra) {
        try {
            service.delete(article);
            ra.addFlashAttribute("message", "The Attribute " + article.toUpperCase(Locale.ROOT) + " has been deleted.");
        } catch (NotFoundException e) {
            ra.addFlashAttribute("message", e.getMessage());
        }
        return "redirect:/profit";
    }
}
