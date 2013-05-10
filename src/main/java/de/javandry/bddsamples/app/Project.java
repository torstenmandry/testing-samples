package de.javandry.bddsamples.app;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@SuppressWarnings("UnusedDeclaration")
public class Project {

  private String name;
  private Date startDate;
  private Date endDate;
  private Person manager;
  private Person controller;
  private Set<Position> positions;

  public Project(String name) {
    this.name = name;
    positions = new HashSet<Position>();
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Date getStartDate() {
    return startDate;
  }

  public void setStartDate(Date startDate) {
    this.startDate = startDate;
  }

  public Date getEndDate() {
    return endDate;
  }

  public void setEndDate(Date endDate) {
    this.endDate = endDate;
  }

  public Person getManager() {
    return manager;
  }

  public void setManager(Person manager) {
    this.manager = manager;
  }

  public Person getController() {
    return controller;
  }

  public void setController(Person controller) {
    this.controller = controller;
  }

  public Set<Position> getPositions() {
    return positions;
  }

  public void addPosition(Position position) {
    positions.add(position);
    position.setProject(this);
  }
}
