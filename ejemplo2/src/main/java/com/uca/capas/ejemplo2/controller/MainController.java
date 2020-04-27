package com.uca.capas.ejemplo2.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController {
	
	@RequestMapping("/ingresar")
	public String index() {
		
		return "ingresarAlumno";
	}
	

	@RequestMapping("/validar")
	public ModelAndView validar(@RequestParam String nombre, @RequestParam String apellido, @RequestParam String  fechaNac, 
			@RequestParam String lugarNac, @RequestParam String institucion, @RequestParam String telefonoF,  @RequestParam String telefonoM) {
		
		  
	
		String err="err";
		List<String> falla = new ArrayList<>();
		ModelAndView error = new ModelAndView();
		if(nombre.length() < 1 ) {
			falla.add("El campo Nombres no puede ser menor a 1 caracter."); }
		
		else if(nombre.length() > 25) {
			falla.add("El campo Nombres no puede ser mayor a 25 caracteres."); }
		
		if(apellido.length() < 1) {
			falla.add("El campo Apellidos no puede ser menor a 1 caracter."); }
		
		else if(apellido.length() > 25) {
			falla.add("El campo Apellidos no puede ser mayor a 25 caracteres."); }
		
		if( fechaNac.isEmpty()) {
			falla.add("El campo Fecha de Nacimiento no puede estar vacio.");
		}
		else {
		
		String limite = "2003-01-01";
		
		DateFormat nac = new SimpleDateFormat("yyyy-mm-dd");
	
		if(nac.parse(fechaNac, new ParsePosition(0)).compareTo(nac.parse(limite, new ParsePosition(0)))< 0) {
			falla.add("El campo Fecha de Nacimiento no puede ser antes de 2003-01-01."); }
		}
		if(lugarNac.length() < 1) {
			falla.add("El campo Lugar de Nacimiento no puede ser menor a 1 caracter."); }
		
		else if(lugarNac.length() > 25) {
			falla.add("El campo Lugar de Nacimiento no puede ser mayor a 25 caracteres."); }
		
		if(institucion.length() < 1) {			
			falla.add("El campo Instituto o colegio de Procedencia no puede ser menor a 1 caracter."); }
		
		else if(institucion.length() > 25) { 
			falla.add("El campo Instituto o colegio de Procedencia no puede ser mayor a 100 caracter.");}
		
		if(telefonoF.length() != 8) {
			falla.add("El campo Telefono Fijo tiene que ser 8 números exactamente.");
			}
			
		
		if(telefonoM.length() != 8) {
			falla.add("El campo Telefono Movil tiene que ser 8 números exactamente.");}
			
		
		
		
		
		
		if(falla.isEmpty()) {			
			error.setViewName("ingresoExito");
		}else {
			error.addObject("fallas", falla);
			error.setViewName("ingresoFallido");
		}
		
					
		return error;
	}

	
	

}
