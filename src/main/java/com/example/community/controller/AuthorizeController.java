package com.example.community.controller;

import com.example.community.dto.AccessTokenDTO;
import com.example.community.dto.GitHubUser;
import com.example.community.provider.GitHubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class AuthorizeController {

    @Autowired
   private GitHubProvider gitHubProvider;


    @GetMapping("/callback")
    public String callback(@RequestParam("code") String code ,@RequestParam("state" ) String state){
        AccessTokenDTO accessTokenDTO=new AccessTokenDTO();
        accessTokenDTO.setClient_id("a5d52009a075f4b193a3");
        accessTokenDTO.setClient_secret("1f58bcee418fd3a64e63d09962b188c771bd167b");
        accessTokenDTO.setCode(code);
        accessTokenDTO.setRedirect_uri("http://localhost:8887/callback");
        accessTokenDTO.setState(state);
        String token = gitHubProvider.geyAccessToken(accessTokenDTO);
        GitHubUser gitHubUser=gitHubProvider.getUser(token);
        System.out.println(gitHubUser.getDisk_usage()+gitHubUser.getId()+gitHubUser.getName());
        return "index";
    }
}
