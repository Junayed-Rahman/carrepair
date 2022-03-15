package com.naoshin.azure.controller;

import com.naoshin.azure.model.Client;
import com.naoshin.azure.model.Mechanic;
import com.naoshin.azure.repository.ClientRepository;
import com.naoshin.azure.repository.MechanicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
public class HomeController {
    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private MechanicRepository mechanicRepository;

    @GetMapping("/home")
    public String getHome() {
        return "index";
    }


    @GetMapping("/login")
    public String getLogin() {
        return "login";
    }



    @GetMapping("/logout")
    public String logout(HttpServletRequest request, Model model){
        HttpSession httpSession = request.getSession();
        httpSession.removeAttribute("activeUser");
        model.addAttribute("logout",true);
        return "login";
    }



    @GetMapping("/service")
    public String getService(Model model) {
        model.addAttribute("client",new Client());
        List<Mechanic> mechanicList = (List<Mechanic>) mechanicRepository.findAll();
        model.addAttribute("mechanicList",mechanicList);
        return "service";
    }

    @PostMapping("/success")
    public String success(@ModelAttribute("client") Client client,Model model){
        client.setRole("client");
        Optional<Client> oldClient = clientRepository.findByNameAndPhone(client.getName(),client.getPhone());

        if(oldClient.isPresent()){
            if(oldClient.get().getAppointmentDate().equals(client.getAppointmentDate())){
                model.addAttribute("sameDate",true);
                return "service";

            }
        }

        Client client1 = clientRepository.save(client);
        model.addAttribute("client",client1);
        Mechanic mechanic = mechanicRepository.findById(client1.getMechanic_id()).get();
        model.addAttribute("mechanic",mechanic);
        mechanicRepository.decreaseClientsCount(mechanic.getId());
        return "success";
    }

    @PostMapping("/editsuccess")
    public String editSuccess(@ModelAttribute("client") Client client,Model model){

        mechanicRepository.increaseClientsCount(client.getMechanic_id());
        clientRepository.updateClient(client.getId(), client.getName(),client.getAppointmentDate(),client.getMechanic_id());
        client = clientRepository.findById(client.getId()).get();
        mechanicRepository.decreaseClientsCount(client.getMechanic_id());


        List<Client> clientList = clientRepository.getClientByRole("client");
        List<Mechanic> mechanicList = new ArrayList<>();

        for (Client client1 : clientList){
            Optional<Mechanic> mechanic = mechanicRepository.findById(client1.getMechanic_id());
            if(mechanic.isPresent()){
                mechanicList.add(mechanic.get());
            }
            else{
                System.out.println("mechanic not found");
            }
        }

        model.addAttribute("clientList",clientList);
        model.addAttribute("mechanicList",mechanicList);
        return "admin";
    }
}


