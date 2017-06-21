package com.sladder.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.CursorIndexOutOfBoundsException;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.sladder.myapplication.constructores.Detalle;
import com.sladder.myapplication.constructores.Producto;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * Created by Andre on 17/07/2015.
 */
public class ControlBD {
    private static final String[] camposProducto = new String[]{"id_producto", "nombre_producto", "precio_u"};
    private static final String[] camposDetalle = new String[]{"id_detalle", "id_producto", "id_carrito", "cantidad", "precio_cantidad"};
    private static final String[] camposCarrito = new String[]{"id_carrito", "nombre_carrito", "fecha", "precio_total"};
    private final Context context;
    private DatabaseHelper DBHelper;
    private SQLiteDatabase db;

    public ControlBD(Context ctx) {
        this.context = ctx;
        DBHelper = new DatabaseHelper(context);
    }

    private static class DatabaseHelper extends SQLiteOpenHelper {
        private static final String BASE_DATOS = "sisventas.s3db";
        private static final int VERSION = 1;

        public DatabaseHelper(Context context) {
            super(context, BASE_DATOS, null, VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            try {
                db.execSQL("CREATE TABLE producto(id_producto INTEGER NOT NULL PRIMARY KEY,nombre_producto VARCHAR(30), precio_u DECIMAL(10,2));");
                db.execSQL("CREATE TABLE detalle(id_detalle INTEGER NOT NULL PRIMARY KEY,id_producto INTEGER,id_carrito INTEGER, cantidad INTEGER, precio_cantidad DECIMAL(10,2), FOREIGN KEY (id_producto) REFERENCES producto(id_producto), FOREIGN KEY (id_carrito) REFERENCES carrito(id_carrito));");
                db.execSQL("CREATE TABLE carrito(id_carrito INTEGER NOT NULL PRIMARY KEY,nombre_carrito VARCHAR(30),fecha DATE, precio_total DECIMAL(10,2));");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            //TODO Auto-generated method stub
        }
    }

    public void abrir() throws SQLException {
        db = DBHelper.getWritableDatabase();
        return;
    }

    public void cerrar() {
        DBHelper.close();
    }


    public String insertar(Producto producto) {
        String regInsertados = "Registro Insertado Nro= ";
        long contador = 0;
        ContentValues prod = new ContentValues();
        prod.put("id_producto", producto.getId_producto());
        prod.put("nombre_producto", producto.getNombre_producto());
        prod.put("precio_u", producto.getPrecio_u());
        contador = db.insert("producto", null, prod);
        if (contador == -1 || contador == 0) {
            regInsertados = "Error al Insertar el registro, Registro Duplicado. Verificar insercion";
        } else {
            regInsertados = regInsertados + contador;
        }
        return regInsertados;
    }

    public Cursor Listar(String filtro) {

        String[] id = {"%"+filtro+"%"};

        Cursor cursor = db.query("producto", camposProducto, "nombre_producto LIKE ?", id, null, null, null);

        if (cursor.moveToFirst()) {

            return cursor;
        }
        else {
            return null;
        }
    }

    public Cursor Listar2(String filtro) {

        String[] id = {filtro};

        Cursor cursor = db.rawQuery("SELECT nombre_producto, precio_u FROM producto WHERE id_producto = ?", id);

        if (cursor.moveToFirst()) {
            return cursor;
        }
        else {
            return null;
        }
    }

    public Cursor Listar_historial(String filtro) {
        String[] id = {filtro};

        Cursor cursor = db.rawQuery("SELECT nombre_carrito, fecha, precio_total FROM carrito WHERE id_carrito = ?", id);

        if (cursor.moveToFirst()) {
            return cursor;
        }
        else {
            return null;
        }
    }

    public Cursor Listar_detalles_carrito(String filtro) {
        String[] id = {filtro};

        Cursor cursor = db.rawQuery("SELECT nombre_carrito, nombre_producto, cantidad, precio_u, precio_cantidad, precio_total, producto.id_producto FROM producto INNER JOIN detalle on " +
                "producto.id_producto=detalle.id_producto INNER JOIN carrito on " +
                "detalle.id_carrito=carrito.id_carrito WHERE carrito.id_carrito = ?", id);

        if (cursor.moveToFirst()) {
            return cursor;
        }
        else {
            return null;
        }
    }

    public String Guardar(String nombre_carrito, String fecha_carrito, BigDecimal precio_total) {

        String regInsertados = "Registro Insertado Nro= ";

        long contador = 0;

        //if(verificarIntegridad(nota,1))

        ContentValues carritos = new ContentValues();
        //carritos.put("id_carrito", "null");
        carritos.put("nombre_carrito", nombre_carrito);
        carritos.put("fecha", fecha_carrito);
        carritos.put("precio_total", String.valueOf(precio_total));

        contador = db.insert("carrito", null, carritos);

        if (contador == -1 || contador == 0) {
            regInsertados = "Error al Insertar el registro, Registro Duplicado. verificar insercion";
        } else {
            regInsertados = regInsertados + contador;
        }
        return regInsertados;
    }

    public String Guardar(int id_producto, int id_carrito, BigInteger cantidad, BigDecimal precio_cantidad){

        String regInsertados="Registro Insertado Nro= ";

        long contador=0;


        ContentValues detalles = new ContentValues();
        //detalles.put("id_detalle", "null");
        detalles.put("id_producto", id_producto);
        detalles.put("id_carrito", id_carrito);
        detalles.put("cantidad", String.valueOf(cantidad));
        detalles.put("precio_cantidad", String.valueOf(precio_cantidad));
        contador=db.insert("detalle", null, detalles);

        if(contador==-1 || contador==0)
        {
            regInsertados= "Error al Insertar el registro, Registro Duplicado. verificar insercion";
        }
        else {
            regInsertados=regInsertados+contador;
        }
        return regInsertados;
    }

    public int Id_carrito() {
        Cursor cursor = db.rawQuery("SELECT MAX(id_carrito) FROM carrito", null);
        cursor.moveToFirst();
        if (cursor.getInt(0)>0) {
            int id_carrito = cursor.getInt(0);
            cursor.close();
            return id_carrito;
        } else {
            int id_carrito = 1;
            cursor.close();
            return id_carrito;
        }
    }

    public int[] Cantidad_carritos() {
        db = DBHelper.getReadableDatabase();
        final Cursor cursor2 = db.rawQuery("SELECT count(*) FROM carrito", null);
        cursor2.moveToFirst();
        final Cursor cursor3 = db.rawQuery("SELECT id_carrito FROM carrito", null);
        cursor3.moveToFirst();

        int[] ids_carritos = new int[cursor2.getInt(0)];
        for (int i = 0; i<cursor2.getInt(0); i++) {
            ids_carritos[i]=cursor3.getInt(0);
            cursor3.moveToNext();
        }
        return ids_carritos;
    }


    public String llenarBDCarnet() {
        final int[] VAid_producto = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,39,40};
        final String[] VAnombre_producto = {"Ajo Cabeza", "Alfajor", "Arroz (1kg)", "Avena", "Canela Entera", "Cebolla ", "Clavo Entero", "Coliflor ","Comida De Perro DogChow", "Durazno", "Frijol ", "Garbanzo", "Harina 1kg", "Hongos ", "Huevo Blanco", "Jabon ",
                "Jugo Lata", "Kiwi", "Lechuga", "Lenteja", "Limon Amarillo", "Maiz Entero  ", "Mandarina 1kg", "Mango 1kg ","Manteca ", "Manzana Roja 1kg", "Manzana Verde 1kg ", "Mayonesa 8 Oz", "Morron Amarillo", "Naranja 1kg", "Papa Blanca 1kg", "Platano Unid. ",
                "Repollo ", "Sal Bolsa", "Sandia", "Soda", "Tomate", "Trigo","Yogurt Litro","Zanahoria"
        };
        final Double[] VAprecio_u = {1.00,0.50,4.00,3.00,1.00,1.30,1.80,2.10,5.20,4.00,3.80,3.00,5.00,1.50,3.80,1.20,
                2.40,3.10,0.70,3.70,2.40,0.70,2.60,1.80,2.10,2.30,2.30,3.10,0.60,2.20,1.80,0.50,1.20,1.10,0.80,1.00,3.20,4.80,5.60,2.40};
        abrir();
        db.execSQL("DELETE FROM producto");

        Producto producto = new Producto();
        for (int i = 0; i < 40; i++) {
            producto.setId_producto(VAid_producto[i]);
            producto.setNombre_producto(VAnombre_producto[i]);
            producto.setPrecio_u(VAprecio_u[i]);
            insertar(producto);
        }

        cerrar();
        return "Guardo Correctamente";
    }
}
