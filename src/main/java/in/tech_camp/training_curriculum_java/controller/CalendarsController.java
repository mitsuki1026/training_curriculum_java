package in.tech_camp.training_curriculum_java.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import in.tech_camp.training_curriculum_java.repository.PlanRepository;
import in.tech_camp.training_curriculum_java.form.PlanForm;
import in.tech_camp.training_curriculum_java.entity.PlanEntity;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class CalendarsController {

  private final PlanRepository planRepository;

  // 1週間のカレンダーと予定が表示されるページ
  @GetMapping("/")
  public String index(Model model) {
    model.addAttribute("planForm", new PlanForm());
    List<Map<String, Object>> weekDays = getWeek(); // lowerキャメル get_week（←C言語）だった（各言語によって命名規則が異なる）
    model.addAttribute("weekDays", weekDays);
    return "calendars/index";
  }

  // 予定の保存
  @PostMapping("/calendars")
  public String create(@ModelAttribute("planForm") @Validated PlanForm planForm, BindingResult result) {
    if (!result.hasErrors()) {
      PlanEntity newPlan = new PlanEntity();
      newPlan.setDate(planForm.getDate());
      newPlan.setPlan(planForm.getPlan());
      planRepository.insert(newPlan);
    }
    return "redirect:/";
  }

  private List<Map<String, Object>> getWeek() {
    List<Map<String, Object>> weekDays = new ArrayList<>();

    LocalDate todaysDate = LocalDate.now();
    List<PlanEntity> plans = planRepository.findByDateBetween(todaysDate, todaysDate.plusDays(6));

    String[] weekLabels = { "(日)", "(月)", "(火)", "(水)", "(木)", "(金)", "(土)" };

    for (int x = 0; x < 7; x++) {
      Map<String, Object> daymap = new HashMap<>();
      LocalDate currentDate = todaysDate.plusDays(x);

      List<String> todayPlans = new ArrayList<>();
      for (PlanEntity plan : plans) {
        if (currentDate.equals(plan.getDate())) {
          todayPlans.add(plan.getPlan());
        }
      }

      daymap.put("month", currentDate.getMonthValue());
      daymap.put("date", currentDate.getDayOfMonth());
      daymap.put("plans", todayPlans);
      daymap.put("dayOfWeek",
          weekLabels[currentDate.getDayOfWeek().getValue() % 7]);

      weekDays.add(daymap);
    }

    return weekDays; // ← ここは必ずメソッドの外（forの外）
  }
}