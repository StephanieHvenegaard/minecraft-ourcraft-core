/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.the_nights.ourcraft.armory.overlay.healthbar;

import com.the_nights.ourcraft.armory.overlay.armorbar.ArmorIconColor;

/**
 *
 * @author Stephanie
 */
public class HealthIcon {
      public Type healthIconType;

    public ArmorIconColor primaryArmorIconColor;
    /*
        Type = HALF:
            The color of the right hand side of the icon
     */
    public ArmorIconColor secondaryArmorIconColor;


    public HealthIcon()
    {
        healthIconType = Type.NONE;
        primaryArmorIconColor = new ArmorIconColor();
        secondaryArmorIconColor = new ArmorIconColor();
    }
    /*
        The type of armor icon to show.
     */
    public enum Type
    {
        NONE,
        HALF,
        FULL
    }  
}
