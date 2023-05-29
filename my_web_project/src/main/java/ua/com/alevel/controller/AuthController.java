package ua.com.alevel.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ua.com.alevel.data.request.AuthData;
import ua.com.alevel.config.security.SecurityService;
import ua.com.alevel.facade.AuthValidatorFacade;
import ua.com.alevel.facade.RegistrationFacade;
import ua.com.alevel.persistence.type.RoleType;
import ua.com.alevel.util.SecurityUtil;

import javax.servlet.http.HttpServletResponse;


@Controller
public class AuthController extends AbstractController {

    private final SecurityService securityService;
    private final RegistrationFacade registrationFacade;
    private final AuthValidatorFacade authValidatorFacade;

    public AuthController(SecurityService securityService, RegistrationFacade registrationFacade, AuthValidatorFacade authValidatorFacade) {
        this.securityService = securityService;
        this.registrationFacade = registrationFacade;
        this.authValidatorFacade = authValidatorFacade;
    }

    @GetMapping("/")
    public String index(Model model) {
        if (SecurityUtil.hasRole(RoleType.ROLE_ADMIN.name())) {
            return "redirect:/admin/home";
        }
        if (SecurityUtil.hasRole(RoleType.ROLE_MANAGER.name())) {
            return "redirect:/manager/home";
        }
        if (SecurityUtil.hasRole(RoleType.ROLE_PERSONAL.name())) {
            return "redirect:/personal/home";
        }
        return "redirect:/main";
    }

    @GetMapping("/login")
    public String login(Model model, String error, String logout) {
        showMessage(model, false);

        boolean authenticated = securityService.isAuthenticated();
        if (authenticated) {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (authentication.getAuthorities().contains(new SimpleGrantedAuthority(RoleType.ROLE_ADMIN.name()))) {
                return handleAdminLogin();
            }
            if (authentication.getAuthorities().contains(new SimpleGrantedAuthority(RoleType.ROLE_MANAGER.name()))) {
                return handleManagerLogin();
            }
            if (authentication.getAuthorities().contains(new SimpleGrantedAuthority(RoleType.ROLE_PERSONAL.name()))) {
                return handlePersonalLogin();
            }
        }
        if (error != null) {
            showError(model, "Your email and password is invalid.");
        }
        if (logout != null) {
            showInfo(model, "You have been logged out successfully.");
        }
        return "login";
    }

    private String handleAdminLogin() {
        return "redirect:/admin/home";
    }
    private String handleManagerLogin() {
        return "redirect:/manager/home";
    }
    private String handlePersonalLogin() {
        return "redirect:/personal/home";
    }

    @GetMapping("/registration")
    public String getRegistrationPage(Model model) {
        showMessage(model, false);
        if (securityService.isAuthenticated()) {
            return redirectProcess(model);
        }
        model.addAttribute("authForm", new AuthData());
        return "/registration";
    }

    private String redirectProcess(Model model) {
        showMessage(model, false);
        if (SecurityUtil.hasRole(RoleType.ROLE_ADMIN.name())) {
            return "redirect:/admin/home";
        }
        if (SecurityUtil.hasRole(RoleType.ROLE_MANAGER.name())) {
            return "redirect:/manager/home";
        }
        if (SecurityUtil.hasRole(RoleType.ROLE_PERSONAL.name())) {
            return "redirect:/personal/home";
        }
        return "redirect:/login";
    }

    @PostMapping("/registration")
    public String registration(@ModelAttribute("authForm")  @Validated AuthData authForm, BindingResult bindingResult, Model model) {
        showMessage(model, false);
        authValidatorFacade.validate(authForm, bindingResult);
        if (bindingResult.hasErrors()) {
            return "registration";
        }
        registrationFacade.registration(authForm);
        securityService.autoLogin(authForm.getEmail(), authForm.getPasswordConfirm());
        return redirectProcess(model);
    }
}
