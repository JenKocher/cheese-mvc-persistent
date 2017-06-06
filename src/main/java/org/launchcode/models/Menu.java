package org.launchcode.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * Created by Jen on 6/6/2017.
 */
@Entity
public class Menu {

    @Id
    @GeneratedValue
    private int id;

    @NotNull
    @Size(min=3, max=15)
    private String name;

    @ManyToMany
    private List<Cheese> cheeses;

    public Menu(){}

    public Menu(String aName){
        this.name=aName;
    }

    public void addItem(Cheese item){
        cheeses.add(item);
    }

    public String getName(){
        return name;
    }

    public void setName(String aName){
        this.name = aName;
    }

    public int getId(){
        return id;
    }

    public void setId(int anId){
        this.id = anId;
    }

    public List<Cheese> getCheeses(){
        return cheeses;
    }
}
