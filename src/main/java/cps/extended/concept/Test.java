/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cps.extended.concept;

import cps.extended.concept.dao.DbManager;
import cps.extended.concept.entities.ProgrammingLang;
import java.io.Serializable;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author tarnschaf
 */
@Named
@ViewScoped
public class Test implements Serializable {

    @Inject
    DbManager dbm;

    public void test() {

        ProgrammingLang lang = new ProgrammingLang();
        lang.setLanguage("dinge");

        dbm.getProgrammingLangDAO().persist(lang);

    }

}
