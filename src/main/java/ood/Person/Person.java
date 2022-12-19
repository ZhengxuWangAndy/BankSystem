/*Person.java: This abstract class represents a person in the Bank system. It stores the UUID,
the String username, and the String password of this person as protected variables.
 */
package ood.Person;

import ood.utils.InputCheck;
import java.util.UUID;

public abstract class Person {
  protected UUID ID;
  protected String username;
  protected String password;
  InputCheck in = new InputCheck();

  public Person(UUID ID, String username, String password) {
    this.ID = ID;
    this.username = username;
    this.password = password;
  }

  public UUID getID() {
    return ID;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername() {
    System.out.println("What's your name?");
    String name = in.getInput();
    this.username = name;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword() {
    System.out.println("Insert new password");
    String pwd = in.getInput();
    this.password = pwd;
  }

  public void write(){
  }
}