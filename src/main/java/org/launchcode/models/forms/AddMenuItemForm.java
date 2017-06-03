package org.launchcode.models.forms;

import org.launchcode.models.Cheese;
import org.launchcode.models.Menu;

import javax.validation.constraints.NotNull;

/**
 * Created by Jen on 6/3/2017.
 */
public class AddMenuItemForm {

    @NotNull
    private int menuId;

    @NotNull
    private int cheeseId;

    private Menu menu;

    private Iterable<Cheese> cheeses;

    public AddMenuItemForm(){}

    public AddMenuItemForm(Iterable<Cheese> cheeses, Menu menu){
        this.cheeses=cheeses;
        this.menu=menu;
    }

    public int getMenuId(){return menuId;}

    public int getCheeseId(){ return cheeseId;}

    public Iterable<Cheese> getCheeses(){
        return cheeses;
    }

    public Menu getMenu(){
        return menu;
    }
}