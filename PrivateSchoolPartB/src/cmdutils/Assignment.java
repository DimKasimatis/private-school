/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cmdutils;

import java.util.Scanner;

/**
 *
 * @author Dim.Kasimatis
 */
public class Assignment {

    Scanner sc;

    public Assignment(Scanner sc) {
        this.sc = sc;
    }

    public models.Assignment askData() {
        Command cmd = new Command();
        models.Assignment assign = new models.Assignment();
        
        
        assign.setTitle(cmd.getField(sc, "Type the assignment's title:"));
        assign.setDecription(cmd.getField(sc, "Type the assignment's stream:"));
        assign.setSubDate(cmd.getDateInput(sc, "Type the assignment's submission date. Use strictly the format 'yyyy-MM-dd'!:"));

        return (assign);
    }
}

