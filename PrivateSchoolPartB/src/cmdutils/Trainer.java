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
public class Trainer {

    Scanner sc;

    public Trainer(Scanner sc) {
        this.sc = sc;
    }

    public models.Trainer askData() {
        Command cmd = new Command();
        models.Trainer trainer = new models.Trainer();

        trainer.setFirstName(cmd.getField(sc, "Type the trainer's First Name:"));
        trainer.setLastName(cmd.getField(sc, "Type the trainer's Last Name:"));
        trainer.setSubject(cmd.getField(sc, "Type trainer's subject:"));

        return (trainer);
    }
}
