package com.naoshin.azure.controller;

import com.naoshin.azure.model.Car;
import com.naoshin.azure.model.Client;
import com.naoshin.azure.model.Mechanic;
import com.naoshin.azure.repository.ClientRepository;
import com.naoshin.azure.repository.MechanicRepository;
import com.naoshin.azure.service.ClientService;
import com.naoshin.azure.service.MechanicService;
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
public class ClientController {
    @Autowired
    public ClientRepository clientRepository;

    @Autowired
    public ClientService clientService;

    @Autowired
    public MechanicService mechanicService;

    @Autowired
    public MechanicRepository mechanicRepository;


    @GetMapping("/register")
    public String showRegisterPage(Model model){
        model.addAttribute("client",new Client());
        return "login";
    }

    @PostMapping("/registersuccess")
    public String registrationSuccess(@ModelAttribute("client") Client client, Model model){
        client.setRole("user");
        Client newClient = clientRepository.save(client);
        model.addAttribute("client",newClient);
        return "login";
    }

    @PostMapping("/loginsuccess")
    public String loginSuccess(@ModelAttribute("client") Client client, Model model,  HttpServletRequest request){
        HttpSession httpSession = request.getSession();
        Optional<Client> oldClient = clientRepository.findByNameAndPassword(client.getName(), client.getPassword());
        if(oldClient.isPresent() && (oldClient.get().getRole().equals("client") |oldClient.get().getRole().equals("user") ) ) {
            model.addAttribute("client", oldClient.get());
            httpSession.setAttribute("activeUser",oldClient.get().getName());
            return "login";
        }
        else if(oldClient.isPresent() && oldClient.get().getRole().equals("admin")){
            httpSession.setAttribute("activeUser",oldClient.get().getName());
            List<Client> clientList = clientRepository.getClientByRole("client");
            List<Mechanic> mechanicList = new ArrayList<>();

            for (Client client1 : clientList){
                Optional<Mechanic> mechanic = mechanicRepository.findById(client1.getMechanic_id());
                if(mechanic.isPresent()){
                    mechanicList.add(mechanic.get());
                }
            }

            model.addAttribute("clientList",clientList);
            model.addAttribute("mechanicList",mechanicList);
            return "admin";
        }
        else{
            throw new IllegalStateException();
        }
    }

    @GetMapping("edit/{cid}/{mid}")
    public String editForm(@PathVariable("cid") long cid, @PathVariable("mid") long mid, Model model){
        System.out.println(cid);
        System.out.println(mid);
        Optional<Client> client = clientRepository.findById(cid);
        if(client.isPresent()){
            System.out.println("client is here");
            model.addAttribute("client",client.get());
        }
        else{

            System.out.println("client not found");
        }
        Optional<Mechanic> mechanic = mechanicRepository.findById(mid);
        if(mechanic.isPresent()){
            model.addAttribute("mechanic",mechanic.get());
        }
        else{
            System.out.println("mechanic not found");
        }
        List<Mechanic> mechanicList = (List<Mechanic>) mechanicRepository.findAll();
        mechanicList = mechanicList.stream().filter(mechanic1 -> mechanic1.getId()!= mid).collect(Collectors.toList());
        model.addAttribute("mechanicList",mechanicList);
        return "tableEditForm";


    }





}
