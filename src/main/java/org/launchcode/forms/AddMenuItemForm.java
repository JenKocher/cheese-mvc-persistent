package org.launchcode.forms;

import org.launchcode.models.Cheese;
import org.launchcode.models.Menu;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Created by Jen on 6/6/2017.
 */
public class AddMenuItemForm {

    private Menu menu;

    private Iterable<Cheese> cheeses;

    @NotNull
    private int menuId;

    @NotNull
    private int cheeseId;

    //getters and setters
    public Menu getMenu(){
        return menu;
    }

    public void setMenu(Menu aMenu){
        this.menu=aMenu;
    }

    public Iterable<Cheese> getCheeses(){
        return cheeses;
    }

    public void setCheeses(Iterable<Cheese> someCheeses){
        this.cheeses=someCheeses;
    }

    public int getMenuId(){
        return menuId;
    }

    public int getCheeseId(){
        return cheeseId;
    }

    //no setters for Ids

    //constructors
    public AddMenuItemForm(){}

    public AddMenuItemForm(Menu aMenu, Iterable<Cheese> someCheeses){
        this.menu=aMenu;
        this.cheeses=someCheeses;
    }
}
