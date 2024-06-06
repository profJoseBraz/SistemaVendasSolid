/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.dao;

import com.mycompany.modelo.ModCategoria;
import com.mycompany.utilidades.BancoDeDadosMySql;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.mycompany.dao.interfaces.IDataAccessObject;

/**
 *
 * @author jose_
 */
public class DaoCategoria extends BancoDeDadosMySql implements IDataAccessObject<ModCategoria>{
    
    private String sql; 
    
    @Override
    public Boolean inserir(ModCategoria modCategoria){
        try{
            sql = "INSERT INTO CATEGORIA (ID, NOME, DESCRICAO) VALUES (?, ?, ?)";
            
            setStatement(getConexao().prepareStatement(sql));
            
            getStatement().setInt(1, modCategoria.getId());
            getStatement().setString(2, modCategoria.getNome());
            getStatement().setString(3, modCategoria.getDescricao());
            
            getStatement().executeUpdate();
            
            return true;
        }catch(Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }
    
    @Override
    public Boolean alterar(ModCategoria modCategoria){
        try{
            sql = "UPDATE CATEGORIA SET NOME = ?, DESCRICAO = ? WHERE ID = ?";
            
            setStatement(getConexao().prepareStatement(sql));
            
            getStatement().setInt(3, modCategoria.getId());
            getStatement().setString(1, modCategoria.getNome());
            getStatement().setString(2, modCategoria.getDescricao());
            
            getStatement().executeUpdate();
            
            return true;
        }catch(SQLException e){
            System.out.println(e.getMessage());
            return false;
        }
    }
    
    @Override
    public Boolean excluir(ModCategoria categoria){
        try{
            sql = "DELETE FROM CATEGORIA WHERE ID = ?";
            
            setStatement(getConexao().prepareStatement(sql));
            
            getStatement().setInt(1, categoria.getId());
            
            getStatement().executeUpdate();
            
            return true;
        }catch(Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }
    
    @Override
    public ResultSet listarTodos(){
        try{
            sql = "SELECT ID, NOME, IFNULL(DESCRICAO, '') FROM CATEGORIA";
            
            setStatement(getConexao().prepareStatement(sql));
            
            setResultado(getStatement().executeQuery());
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        
        return getResultado();
    }
    
    @Override
    public ResultSet listarPorId(int id){
        try{
            sql = "SELECT ID, NOME, IFNULL(DESCRICAO, '') FROM CATEGORIA WHERE ID = ?";
            
            setStatement(getConexao().prepareStatement(sql));
            
            getStatement().setInt(1, id);
            
            setResultado(getStatement().executeQuery());
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        
        return getResultado();
    }
    
    public ResultSet listarPorNome(String nome){
        try{
            sql = "SELECT ID, NOME, IFNULL(DESCRICAO, '') FROM CATEGORIA WHERE NOME LIKE ?";
            
            setStatement(getConexao().prepareStatement(sql));
            
            getStatement().setString(1, nome + "%");
            
            setResultado(getStatement().executeQuery());
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        
        return getResultado();
    }
    
    public ResultSet listarPorDescricao(String descricao){
        try{
            sql = "SELECT ID, NOME, IFNULL(DESCRICAO, '') FROM CATEGORIA WHERE DESCRICAO LIKE ?";
            
            setStatement(getConexao().prepareStatement(sql));
            
            getStatement().setString(1, descricao + "%");
            
            setResultado(getStatement().executeQuery());
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        
        return getResultado();
    }
    
    public int buscarProximoId(){
        int id = 0;
        
        try{
            sql = "SELECT IFNULL(MAX(ID), 0) + 1 FROM CATEGORIA";
            
            setStatement(getConexao().prepareStatement(sql));
            
            setResultado(getStatement().executeQuery());
            
            getResultado().next(); //Move para o primeiro registro.
            
            id = getResultado().getInt(1); //Pega o valor retornado na consulta
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        
        return id;
    }
}
