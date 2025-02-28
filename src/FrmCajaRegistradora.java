
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;

public class FrmCajaRegistradora extends JFrame {
    JTable tblTablaDevolucion;
    DefaultTableModel datosDevueltaTabla;
    JComboBox cmbOpciones;
    JTextField txtCantidad;
    JTextField txtCantDevolucion;
    JTextField txtValorSaldo;

    Integer[] opciones = new Integer[] { 100000, 50000, 20000, 10000, 5000, 2000, 1000, 500, 200, 100, 50 };
    String[] encabezados = new String[] { "Cantidad", "Presentación", "Denominción" };

    // metodo constructor
    public FrmCajaRegistradora() {
        setSize(400, 400);
        setTitle("Caja Registradora");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        JLabel LabelDenomimacion = new JLabel("Denominación");
        LabelDenomimacion.setBounds(10, 10, 250, 25);
        getContentPane().add(LabelDenomimacion);

        cmbOpciones = new JComboBox();
        DefaultComboBoxModel modelDenom = new DefaultComboBoxModel(opciones);
        cmbOpciones.setModel(modelDenom);
        cmbOpciones.setBounds(150, 10, 100, 25);
        getContentPane().add(cmbOpciones);

        JButton btnActualizar = new JButton("Actualizar existencia");
        btnActualizar.setBounds(10, 50, 200, 25);
        getContentPane().add(btnActualizar);

        txtCantidad = new JTextField();
        txtCantidad.setBounds(250, 50, 100, 25);
        getContentPane().add(txtCantidad);

        JLabel lblValor = new JLabel("Valor a devolver");
        lblValor.setBounds(10, 100, 250, 25);
        getContentPane().add(lblValor);

        txtCantDevolucion = new JTextField();
        txtCantDevolucion.setBounds(110, 100, 100, 25);
        getContentPane().add(txtCantDevolucion);

        JButton btnDevolver = new JButton("Devolver");
        btnDevolver.setBounds(230, 100, 100, 25);
        getContentPane().add(btnDevolver);

        tblTablaDevolucion = new JTable();
        JScrollPane spTablaDevolucion = new JScrollPane(tblTablaDevolucion);
        spTablaDevolucion.setBounds(10, 150, 350, 200);
        getContentPane().add(spTablaDevolucion);

        datosDevueltaTabla = new DefaultTableModel(new Object[][] {}, encabezados);
        tblTablaDevolucion.setModel(datosDevueltaTabla);

        btnActualizar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                actualizarCaja();
            }

        });

        btnDevolver.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Devolucion();
            }

        });

    }

    int[] CantidadBilletes = new int[10];

    private void actualizarCaja() {
        try {
            int Selectdenominacion = (int) cmbOpciones.getSelectedItem();
            int cantidad = Integer.parseInt(txtCantidad.getText().trim());

            if (cantidad < 0) {
                JOptionPane.showMessageDialog(null, "Debe ingresar un número positivo.");
                return;
            }

            for (int i = 0; i < opciones.length; i++) {
                if (opciones[i] == Selectdenominacion) {
                    CantidadBilletes[i] = cantidad;
                    JOptionPane.showMessageDialog(null, "Cantidad actualizada correctamente.");
                    txtCantidad.setText("");
                    return;
                }
            }
        } catch (Exception ex) {
            txtCantidad.setText("");
            JOptionPane.showMessageDialog(null, "Debe digitar un valor numérico válido.");
        }
    }

    private void Devolucion() {
        try {

            
            int cantidad = Integer.parseInt(txtCantDevolucion.getText().trim());
            int modulo = cantidad % 10000;
            int moduloPesos = cantidad % 1000;
            int valor = cantidad - modulo;
            datosDevueltaTabla.setRowCount(0);

            devolverMiles(valor);
            devolverMonedas(moduloPesos);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error: " + e.getMessage());
        }
    }
    
    private void devolverMiles(int valor) {
        int acumulador = 0;
        for (int i = 0; i <= 5; i++) { 
            int billete = opciones[i];
    
            while (acumulador + billete <= valor && CantidadBilletes[i] > 0) {
                boolean encontrado = false;
    
                for (int j = 0; j < datosDevueltaTabla.getRowCount(); j++) {
                    if ((int) datosDevueltaTabla.getValueAt(j, 2) == billete) {
                        int cantidadExistente = (int) datosDevueltaTabla.getValueAt(j, 0);
                        datosDevueltaTabla.setValueAt(cantidadExistente + 1, j, 0);
                        encontrado = true;
                        break;
                    }
                }
    
                if (!encontrado) {
                    datosDevueltaTabla.addRow(new Object[]{1, "billete", billete});
                }
    
                acumulador += billete;
                CantidadBilletes[i]--;
            }
        }
    }
    
    
    
    private void devolverMonedas(int moduloPesos) {
        int acumuladorMonedas = 0;
    
        for (int i = 6; i < opciones.length; i++) { 
            int moneda = opciones[i];
    
            while (acumuladorMonedas + moneda <= moduloPesos && CantidadBilletes[i] > 0) {
                boolean encontrado = false;
    
                for (int j = 0; j < datosDevueltaTabla.getRowCount(); j++) {
                    if ((int) datosDevueltaTabla.getValueAt(j, 2) == moneda) {
                        int cantidadExistente = (int) datosDevueltaTabla.getValueAt(j, 0);
                        datosDevueltaTabla.setValueAt(cantidadExistente + 1, j, 0);
                        encontrado = true;
                        break;
                    }
                }
    
                if (!encontrado) {
                    datosDevueltaTabla.addRow(new Object[]{1, "moneda", moneda});
                }
    
                acumuladorMonedas += moneda;
                CantidadBilletes[i]--;
            }
        }
    }
}   