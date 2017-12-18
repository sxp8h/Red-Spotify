package beans;

import java.util.ArrayList;

import interfaces.beans;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.ConnectionOracle;

//
//  ::::::::  :::    ::: :::::::::   ::::::::  :::    :::
// :+:    :+: :+:    :+: :+:    :+: :+:    :+: :+:    :+:
// +:+         +:+  +:+  +:+    +:+ +:+    +:+ +:+    +:+
// +#++:++#++   +#++:+   +#++:++#+   +#++:++#  +#++:++#++
//        +#+  +#+  +#+  +#+        +#+    +#+ +#+    +#+
// #+#    #+# #+#    #+# #+#        #+#    #+# #+#    #+#
//  ########  ###    ### ###         ########  ###    ###
//
//          --------Created by SXP8H--------

public class Artist implements beans<Artist, Integer>{

    // Active or Deactive debug mode
    private static final boolean debug = false;

    // Connection 
    private ConnectionOracle database = new ConnectionOracle();
    private Connection con;

    // TABLE Artist
    private final String TABLE_NAME = "RED_ARTISTS";
    private final String ID_ARTIST = "ID_ARTIST";
    private final String NAME = "NAME";
    private final String URL = "URL";
    private final String NATIONALITY = "NATIONALITY";


    // Sql headers
    private final String SQL_CREATE = "INSERT INTO " + TABLE_NAME + " ( " + NAME + ", " + URL + ", " + NATIONALITY + " ) VALUES ";
    private final String SQL_READ = "SELECT * FROM " + TABLE_NAME;
    private final String SQL_UPDATE = "UPDATE " + TABLE_NAME + " SET ";
    private final String SQL_DELETE = "DELETE FROM " + TABLE_NAME;

    // Atributes
    private int idArtist;
    private String name;
    private String url;
    private String nationality;

    // Constructor
    public Artist(String name, String url) {
        this.name = name;
        this.url = url;
    }
    public Artist(String name, String url, String nationality) {
        this.name = name;
        this.url = url;
        this.nationality = nationality;
    }
    public Artist() {}

    // Getter and Setter
    @Override
    public String toString() {
        return "Artist{" + "idArtist=" + idArtist + ", name=" + name + ", url=" + url + ", nationality=" + nationality + '}';
    }

    public int getIdArtist() {
            return idArtist;
    }
    public void setIdArtist(int idArtist) {
            this.idArtist = idArtist;
    }
    public String getName() {
            return name;
    }
    public void setName(String name) {
            this.name = name;
    }
    public String getUrl() {
            return url;
    }
    public void setUrl(String url) {
            this.url = url;
    }
    public String getNationality() {
            return nationality;
    }
    public void setNationality(String nationality) {
            this.nationality = nationality;
    }
    
    // Methods	
    @Override
    public int create(Artist bean) {
        int result;
        // INSERT INTO table_name (column1, column2, column3, ...)
        // VALUES (value1, value2, value3, ...);

        // int idUser = bean.getIdArtist();
        String name = (bean.getName() == null || bean.getName().equals("")) ? "<EMPTY_NAME>" : bean.getName();
        String url = (bean.getUrl()== null || bean.getUrl().equals("")) ? "<EMPTY_URL>" : bean.getUrl();
        String nationality = (bean.getNationality()== null || bean.getNationality().equals("")) ? "<EMPTY_NATIONALITY>" : bean.getNationality();

        System.out.println(name);
        System.out.println(url);
        System.out.println(nationality);

        String sql = SQL_CREATE;
        sql += "(?, ?, ?)";

        System.out.println(sql);

        try {

            con = database.connect();
            PreparedStatement createSt = con.prepareStatement(sql);
            createSt.setString(1, name);
            createSt.setString(2, url);
            createSt.setString(3, nationality);
            result = createSt.executeUpdate();

        } catch (Exception ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
            return 1;
        }

        return result;
    }
    
    @Override
    public ArrayList<Artist> read() {
        ArrayList<Artist> result = new ArrayList();
        ResultSet rs;

        String sql = SQL_READ;
         
        System.out.println(sql);

        try {
            con = database.connect();
            PreparedStatement readSt = con.prepareStatement(sql);
            rs = readSt.executeQuery();
            
            while(rs.next()){
                Artist aux = new Artist();
                
                aux.setIdArtist(rs.getInt(ID_ARTIST));
                aux.setName(rs.getString(NAME));
                aux.setUrl(rs.getString(URL));
                aux.setNationality(rs.getString(NATIONALITY));
                
                result.add(aux);
            }
            
        } catch (Exception ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        
        return result;
    }
            @Override
    public ArrayList<Artist> read(int id) {
        ArrayList<Artist> result = new ArrayList();
        ResultSet rs;
        // INSERT INTO table_name (column1, column2, column3, ...)
        // VALUES (value1, value2, value3, ...);

        String sql = SQL_READ + " WHERE " + ID_ARTIST + " = ?";
         
        System.out.println(sql);

        try {
            con = database.connect();
            PreparedStatement readSt = con.prepareStatement(sql);
            readSt.setInt(1, id);
            rs = readSt.executeQuery();
            
            while(rs.next()){
                Artist aux = new Artist();
                
                aux.setIdArtist(rs.getInt(ID_ARTIST));
                aux.setName(rs.getString(NAME));
                aux.setUrl(rs.getString(URL));
                aux.setNationality(rs.getString(NATIONALITY));
                
                result.add(aux);
            }
            
        } catch (Exception ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        
        return result;
    }

    public ArrayList<Artist> read(String name, String nationality) {
        ArrayList<Artist> result = new ArrayList();
        ResultSet rs;
        boolean nameEmpty = false;
        boolean nationalityEmpty = false;
                
        String sql = SQL_READ + " WHERE " + NAME + " LIKE ? AND " +  NATIONALITY + " LIKE ?";
        System.out.println(sql);
        System.out.println(name);
        System.out.println(nationality);
        try {
            con = database.connect();
            PreparedStatement readSt = con.prepareStatement(sql);

            readSt.setString(1, "%"+name+"%");
            readSt.setString(2, "%"+nationality+"%");
            rs = readSt.executeQuery();

            while(rs.next()){
                Artist aux = new Artist();

                aux.setIdArtist(rs.getInt(ID_ARTIST));
                aux.setName(rs.getString(NAME));
                aux.setUrl(rs.getString(URL));
                aux.setNationality(rs.getString(NATIONALITY));

                result.add(aux);
            }
        } catch (Exception ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        
        return result;
    }
    public ArrayList<Artist> read(String name) {
        ArrayList<Artist> result = new ArrayList();
        ResultSet rs;
        // INSERT INTO table_name (column1, column2, column3, ...)
        // VALUES (value1, value2, value3, ...);

        String sql = SQL_READ + " WHERE " + NAME + " like ?";
        System.out.println(sql);
        try {
            con = database.connect();
            PreparedStatement readSt = con.prepareStatement(sql);
            readSt.setString(1, name);
            readSt.setString(2, nationality);
            rs = readSt.executeQuery();
            while(rs.next()){
                Artist aux = new Artist();

                aux.setIdArtist(rs.getInt(ID_ARTIST));
                aux.setName(rs.getString(NAME));
                aux.setUrl(rs.getString(URL));
                aux.setNationality(rs.getString(NATIONALITY));

                result.add(aux);
            }
        } catch (Exception ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        
        return result;
    }
    
    @Override
    public int update(Artist bean) {
        // UPDATE table_name
        // SET column1 = value1, column2 = value2, ...
        // WHERE condition; 
        
        int result;
        
        int idArtist = bean.getIdArtist();
        String name = (bean.getName() == null || bean.getName().equals("")) ? "<EMPTY_NAME>" : bean.getName();
        String url = (bean.getUrl()== null || bean.getUrl().equals("")) ? "<EMPTY_URL>" : bean.getUrl();
        String nationality = (bean.getNationality()== null || bean.getNationality().equals("")) ? "<EMPTY_NATIONALITY>" : bean.getNationality();
        
        System.out.println(idArtist);
        System.out.println(name);
        System.out.println(url);
        System.out.println(nationality);

        String sql = SQL_UPDATE;
        sql += " " + NAME + " = ?, " + URL + " = ?, " + NATIONALITY + " = ? ";
        sql += " WHERE " + ID_ARTIST + " = ?";
         
        System.out.println(sql);

        try {
            
            con = database.connect();
            PreparedStatement updateSt = con.prepareStatement(sql);
            updateSt.setString(1, name);
            updateSt.setString(2, url);
            updateSt.setString(3, nationality);
            updateSt.setInt(4, idArtist);
            result = updateSt.executeUpdate();
            
        } catch (Exception ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
            return 1;
        }
        
        return result;
    }
    @Override
    public int delete(Integer integer) {
        int result;
        String sql = SQL_DELETE + " WHERE " + ID_ARTIST + " = ?";
         
        System.out.println(sql);
        System.out.println("id === " + integer);

        try {
            con = database.connect();
            PreparedStatement deleteSt = con.prepareStatement(sql);
            deleteSt.setInt(1, integer);
            result = deleteSt.executeUpdate();
            
        } catch (Exception ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
            return -1;
        }
        
        return result;
    }
    @Override
    public int exist(Integer integer) {
            // TODO Auto-generated method stub
            return 0;
    }

    public String toArrayJson(ArrayList<Artist> list){
        String resp = "";
        int i = 0 ;
        resp+="[";
        for (Artist artist : list) {
            resp+="{";
            resp+= "'" + ID_ARTIST + "'";
            resp+= ":";
            resp+= "'" + artist.getIdArtist()+ "'";
            resp+=",";
            resp+= "'" + NAME +"'";
            resp+= ":";
            resp+="'" + artist.getName() + "'";
            resp+=",";
            resp+= "'" + URL + "'";
            resp+= ":";
            resp+="'" + artist.getUrl()+ "'";
            resp+=",";
            resp+= "'" + NATIONALITY +"'";
            resp+= ":";
            resp+="'" + artist.getNationality() + "'";
            resp+="}";
            resp += (i+1==list.size())?"":",";
            i++;
        }
        resp+="]";
        
        return resp;
    }

    public static void main(String[] args) {
        if(debug){
            System.out.println("##########################################################");
            System.out.println("#  ::::::::  :::    ::: :::::::::   ::::::::  :::    ::: #");
            System.out.println("# :+:    :+: :+:    :+: :+:    :+: :+:    :+: :+:    :+: #");
            System.out.println("# +:+         +:+  +:+  +:+    +:+ +:+    +:+ +:+    +:+ #");
            System.out.println("# +#++:++#++   +#++:+   +#++:++#+   +#++:++#  +#++:++#++ #");
            System.out.println("#        +#+  +#+  +#+  +#+        +#+    +#+ +#+    +#+ #");
            System.out.println("#        +#+  +#+  +#+  +#+        +#+    +#+ +#+    +#+ #");
            System.out.println("#  ########  ###    ### ###         ########  ###    ### #");
            System.out.println("##########################################################");
            System.out.println("------------------- TEST ARTISTS -------------------------");
            System.out.println("\n\n");
            System.out.println("  @ Create [WORK]");

            Artist artist = new Artist();
            artist.setName("prueba");
            artist.setUrl("prueba");
            System.out.println("    ~CREATED ARTISTS: " + artist.create(artist));
            System.out.println("\n");System.out.println("\n");

            ArrayList<Artist> vA = new ArrayList<Artist>();
            System.out.println("  @ Read (all)");
            vA = artist.read();
            try {
                TimeUnit.SECONDS.sleep(5);
                System.out.println("    ~READ ARTIST:");
                for (Iterator<Artist> i = vA.iterator(); i.hasNext();) {
                    Artist ar = i.next();
                    System.out.println(ar.toString());
                }
            } catch (Exception ex) {
                Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
            }
            vA.clear();
            System.out.println("\n");System.out.println("\n");
            
            System.out.println("  @ Read (only one id[id=1]) [WORK]");
            vA = artist.read(1);
            try {
                TimeUnit.SECONDS.sleep(5);
                System.out.println("    ~READ ARTIST:");
                for (Iterator<Artist> i = vA.iterator(); i.hasNext();) {
                    Artist ar = i.next();
                    System.out.println(ar.toString());
                }
            } catch (Exception ex) {
                Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
            }
            vA.clear();
            System.out.println("\n");System.out.println("\n");
            
            System.out.println("  @ Read (only one id[id=3]) [WORK]");
            vA = artist.read(3);
            try {
                TimeUnit.SECONDS.sleep(5);
                System.out.println("    ~READ ARTIST:");
                for (Iterator<Artist> i = vA.iterator(); i.hasNext();) {
                    Artist ar = i.next();
                    System.out.println(ar.toString());
                }
            } catch (Exception ex) {
                Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
            }
            vA.clear();
            System.out.println("\n");System.out.println("\n");
            
            System.out.println("  @ Update (id=3) [WORK]");
            try {
                TimeUnit.SECONDS.sleep(5);
                System.out.println("    ~UPDATED ARTIST :");
                artist.setIdArtist(3);
                artist.setName("pruebaUpdate");
                artist.setUrl("pruebaUpdate");
                System.out.println(artist.update(artist));
            } catch (Exception ex) {
                Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                artist.setIdArtist(3);
                artist.setName("prueba");
                artist.setUrl("prueba");
                artist.update(artist);
            } catch (Exception ex) {
                Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println("\n");System.out.println("\n");
            
            // DELETE PART START
            Artist artistDel = new Artist();
            artistDel.setName("AUTODELETE");
            artistDel.setUrl("AUTODELETE");
            artistDel.create(artistDel);

            // Getting last id 
            int value = 0;
            ResultSet rs;
            String sql = "SELECT * FROM RED_ARTISTS ";

            try {
                ConnectionOracle databaseStatic = new ConnectionOracle();
                Connection conStatic;
                conStatic = databaseStatic.connect();
                PreparedStatement readSt = conStatic.prepareStatement(sql);
                rs = readSt.executeQuery();

                while(rs.next()){
                    Artist aux = new Artist();

                    aux.setIdArtist(rs.getInt("ID_ARTIST"));
                    aux.setName(rs.getString("NAME"));
                    aux.setUrl(rs.getString("URL"));

                    if(rs.next()){
                        artistDel.setIdArtist(rs.getInt("ID_ARTIST")+1);
                        System.out.println(rs.getInt("ID_ARTIST"));
                    }
                }

            } catch (Exception ex) {
                Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println("\n");System.out.println("\n");
            
            System.out.println("  @ Delete (id===???) [WORK]");
            try {
                TimeUnit.SECONDS.sleep(10);
                System.out.println("    ~DELETED ARTIST :");
                System.out.println(artistDel.delete(artistDel.getIdArtist()));
            } catch (Exception ex) {
                Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
            }
            // DELETE PART END
        }
    }



}
