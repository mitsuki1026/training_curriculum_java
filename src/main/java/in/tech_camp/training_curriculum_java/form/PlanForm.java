package in.tech_camp.training_curriculum_java.form;

import java.time.LocalDate;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


public class PlanForm {
  @NotBlank(message = "Email can't be blank")
  private String plan;
  public String getPlan() {
    return plan;
  }

  public void setPlan(String plan) {
    this.plan = plan;
  }

  @NotNull(message = "Date can't be blank")
  private LocalDate date;
  public LocalDate getDate() {
    return date;
  }

  public void setDate(LocalDate date) {
    this.date = date;
  }
}

