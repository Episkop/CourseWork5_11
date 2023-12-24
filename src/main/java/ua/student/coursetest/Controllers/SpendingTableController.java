package ua.student.coursetest.Controllers;

import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ua.student.coursetest.Exception.AlreadyExistException;
import ua.student.coursetest.Exception.NotFoundException;
import ua.student.coursetest.Model.SpendingModel;
import ua.student.coursetest.Services.SpendingService;

import java.util.Locale;
@Controller
public class SpendingTableController {

    private final SpendingService spendingService;

    public SpendingTableController(SpendingService spendingService) {
        this.spendingService = spendingService;
    }

    private String getEmail(OAuth2AuthenticationToken auth) {
        return (String) auth.getPrincipal().getAttributes().get("email");
    }

    @GetMapping("/newSpending")
    public String showNewFormSpending(Model model) {
        model.addAttribute("spending", new SpendingModel());
        model.addAttribute("pageTitle", "Add New Spending Line");
        return "save_form_spending";
    }

    @PostMapping("/saveSpending")
    public String save(@ModelAttribute("spending") SpendingModel model, RedirectAttributes ra,
                       OAuth2AuthenticationToken auth) {//добавил @modelAttribute и стало сохранять
        String email = getEmail(auth);
        try {
            spendingService.saveSpending(email,model);
            ra.addFlashAttribute("message", "The new Line with article " + model.getArticle().toUpperCase(Locale.ROOT) + " has been saved successfully.");
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

    @GetMapping("/updateSpending/{article}")
    public String updateFormSpending(@PathVariable("article") String article, Model model, RedirectAttributes ra,
                                     OAuth2AuthenticationToken auth) {
        String email = getEmail(auth);
        try {
            model.addAttribute("spending", spendingService.findSpending(email,article));
            model.addAttribute("pageTitle", "Add New Spending Line");
            return "edit_form_spending";
        } catch (NotFoundException e) {
            ra.addFlashAttribute("message", e.getMessage());
            return "redirect:/profit";
        }
    }

    @PostMapping("/saveUpdateSpending/{article}")
    public String update(@ModelAttribute("spending") SpendingModel spendingModel,@PathVariable ("article") String article,
                         RedirectAttributes ra,OAuth2AuthenticationToken auth) {
        String email = getEmail(auth);
        try {
            spendingService.updateSpending(email,spendingModel);
            ra.addFlashAttribute("message", "The new Line with article " + spendingModel.getArticle().toUpperCase(Locale.ROOT) + " has been saved successfully.");
        } catch (NotFoundException e) {
            throw new RuntimeException(e);
        }
        return "redirect:/profit";
    }

    @GetMapping("/deleteSpending/{article}")
    public String deleteUser(@PathVariable("article") String article, RedirectAttributes ra,OAuth2AuthenticationToken auth) {
        String email = getEmail(auth);
        try {
            spendingService.deleteSpending(email,article);
            ra.addFlashAttribute("message", "The Attribute " + article.toUpperCase(Locale.ROOT) + " has been deleted.");
        } catch (NotFoundException e) {
            ra.addFlashAttribute("message", e.getMessage());
        }
        return "redirect:/profit";
    }
}


