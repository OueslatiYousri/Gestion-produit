package com.mi.Gestion_Stock.Controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
//import com.mi.Gestion_Stock.entities.Article;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mi.Gestion_Stock.entities.Provider;
import com.mi.Gestion_Stock.repositories.ProviderRepository;

import java.util.List;

import javax.validation.Valid;
@Controller
@RequestMapping("/Gestion_stock/")
public class ProviderController {
	
	@RequestMapping("/Gestion_stock/")
	public String index() {
		return "redirect:list_providers";
	}

	
	
	private  ProviderRepository providerRepository;
	
    @Autowired
    public ProviderController(ProviderRepository providerRepository) {
        this.providerRepository = providerRepository;
    }

    
    @RequestMapping(value="list_providers")
    //@ResponseBody
    public String listProviders(Model model,@RequestParam(name="page",defaultValue = "0") int p,@RequestParam(name="size",defaultValue = "2") int s
    		,@RequestParam(name="mc",defaultValue = "") String mc
    		) {
    	
    	

    	Page<Provider> lp =  providerRepository.chercher("%"+mc+"%", PageRequest.of(p, s) );
    	if(lp.getSize()==0)
    		lp=null;
    	
        model.addAttribute("providers", lp.getContent());
        int [] tabpage=new int[lp.getTotalPages()];
        model.addAttribute("tabpage", tabpage);
        model.addAttribute("mc", mc);
        model.addAttribute("size", s);
        model.addAttribute("pagecourante", p);
        return "/listProviders";
        
    }
    
    @GetMapping("add_providers")
    public String showAddProviderForm(Model model) {
    	Provider provider = new Provider();// object dont la valeur des attributs par defaut
    	model.addAttribute("provider", provider);
        return "/provider/addProvider";
    }
    
    @PostMapping("save_providers")
    public String addProvider(@Valid Provider provider, BindingResult result) {
        if (result.hasErrors()) {
            return "/provider/addProvider";
        }
        providerRepository.save(provider);
        return "redirect:list_providers";
    }

    
    @GetMapping("delete_providers/{id}")
    public String deleteProvider(@PathVariable("id") long id , int page,int size,String mc ) {
    	
    	

    	
        Provider provider = providerRepository.findById(id)
            .orElseThrow(()-> new IllegalArgumentException("Invalid provider Id:" + id));
        
        
        providerRepository.delete(provider);

        return "redirect:../list_providers?page="+page+"&size="+size+"&mc="+mc;
    }
    
    
    @GetMapping("edit_providers/{id}")
    public String showProviderFormToUpdate(@PathVariable("id") long id, 
    		Model model,@RequestParam(name="page",defaultValue = "0") int p,
    		
    		@RequestParam(name="size",defaultValue = "2")int size,
    		@RequestParam(name="mc",defaultValue = "") String mc) {
        Provider provider = providerRepository.findById(id)
            .orElseThrow(()->new IllegalArgumentException("Invalid provider Id:" + id));
        model.addAttribute("mc", mc);
        model.addAttribute("size", size);
        model.addAttribute("pagecourante", p);
        model.addAttribute("provider", provider);
    	System.out.println("dd");
        return "/provider/updateProvider";
    }


    
    @PostMapping("update_providers")
    public String updateProvider(@Valid Provider provider, BindingResult result, Model model ,@RequestParam(name="page",defaultValue = "0") int page,
    		
    		@RequestParam(name="size",defaultValue = "2")int size,
    		@RequestParam(name="mc",defaultValue = "") String mc) {
    	if (result.hasErrors()) {
    		System.out.println(result.getAllErrors());
            return "/provider/updateProvider";
        }
    	 model.addAttribute("mc", mc);
         model.addAttribute("size", size);
         model.addAttribute("pagecourante", page);
    	providerRepository.save(provider);
    	 return "redirect:./list_providers?page="+page+"&size="+size+"&mc="+mc;
    	
    }
    
   @GetMapping("show_providers/{id}")
	public String showProvider(@PathVariable("id") long id, Model model,@RequestParam(name="page",defaultValue = "0") int p,
    		
    		@RequestParam(name="size",defaultValue = "2")int size,
    		@RequestParam(name="mc",defaultValue = "") String mc) {
		Provider provider = providerRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid provider Id:" + id));
		
		//List<Article> articles = providerRepository.findArticlesByProvider(id);
		/*for (Article a : articles)
			System.out.println("Article = " + a.getLabel());
		
		model.addAttribute("articles", articles);*/
		 model.addAttribute("mc", mc);
	        model.addAttribute("size", size);
	        model.addAttribute("pagecourante", p);
		model.addAttribute("provider", provider);
		return "provider/showProvider";
	}

}
