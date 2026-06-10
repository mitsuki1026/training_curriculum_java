package in.tech_camp.training_curriculum_java.entity;

import java.time.LocalDate;

public class PlanEntity {
  private Long id;
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  private String plan;
  public String getPlan() {
    return plan;
  }

  public void setPlan(String plan) {
    this.plan = plan;
  }

  private LocalDate date;
  public LocalDate getDate() {
    return date;
  }

  public void setDate(LocalDate date) {
    this.date = date;
  }
}
