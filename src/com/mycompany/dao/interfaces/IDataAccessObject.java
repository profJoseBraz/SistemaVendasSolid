/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.dao.interfaces;
import java.sql.ResultSet;
/**
 *
 * @author 10156
 */
public interface IDataAccessObject<T> {
    public Boolean inserir(T entity);
    
    public Boolean alterar(T entity);
    
    public Boolean excluir(T entity);
    
    public ResultSet listarTodos();
    
    public ResultSet listarPorId(int id);
    
    public int buscarProximoId();
}
