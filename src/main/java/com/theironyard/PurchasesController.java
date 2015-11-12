package com.theironyard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileReader;

/**
 * Created by MattBrown on 11/11/15.
 */
@Controller
public class PurchasesController {
    @Autowired
    CustomerRepository customers;

    @Autowired
    PurchaseRepository purchases;

    @PostConstruct
    public void init() {
        String fileContent = readFile("customers.csv");
        String fileContentTwo = readFile("purchases.csv");
        String[] lines = fileContent.split("\n");
        String[] linesTwo = fileContentTwo.split("\n");

        if (customers.count() == 0) {
            for (String line : lines) {
                if (line == lines[0])
                    continue;
                String[] columns = line.split(",");
                Customer customer = new Customer();
                customer.name = columns[0];
                customer.email = columns[1];
                customers.save(customer);
            }
            if (purchases.count() == 0) {
                for (String line : linesTwo) {
                    if (line == linesTwo[0])
                        continue;
                    String[] columnsTwo = line.split(",");
                    Purchase purchase = new Purchase();
                    purchase.date = columnsTwo[1];
                    purchase.creditCard = columnsTwo[2];
                    purchase.cvv = columnsTwo[3];
                    purchase.category = columnsTwo[4];
                    purchase.customer = customers.findOne(Integer.valueOf(columnsTwo[0]));
                    purchases.save(purchase);
                }
            }
        }
    }
    static String readFile(String fileName) {
        File f = new File(fileName);
        try {
            FileReader fr = new FileReader(f);
            int fileSize = (int) f.length();
            char[] fileContent = new char[fileSize];
            fr.read(fileContent);
            return new String(fileContent);
        } catch (Exception e) {
            return null;
        }
    }
    @RequestMapping("/")
    public String home(Model model,
                       String name,
                       String email,
                       String date,
                       String creditCard,
                       String cvv,
                       String category
                       ){
        if(name != null){
            model.addAttribute(name);
        }
        else if(email != null){
            model.addAttribute(email);
        }
        else if(date != null){
            model.addAttribute(date);
        }
        else if(creditCard != null){
            model.addAttribute(creditCard);
        }
        else if(cvv != null){
            model.addAttribute(cvv);
        }
        else if(category != null){
            model.addAttribute("purchases", purchases.findByCategory(category));
        }
        else {
            model.addAttribute("purchases", purchases.findAll());
            model.addAttribute("customers", customers.findAll());
        }

        return "home";
    }

}







//Customer customer = customers.findOneByName("Admin");
//if (customer == null){
//customer = new Customer();
//customer.name;
// }