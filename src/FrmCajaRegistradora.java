
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

    int[] CantidadBilletes = new int[opciones.length];

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
            datosDevueltaTabla.setRowCount(0);

            int acumulador = 0;
            boolean EntregoCambio = false;

            for (int i = 0; i < opciones.length; i++) {
                int denominacion = opciones[i];
                String tipo = (denominacion >= 1000) ? "billete" : "moneda";

                while (acumulador + denominacion <= cantidad && CantidadBilletes[i] > 0) {
                    boolean encontrado = false;

                    for (int j = 0; j < datosDevueltaTabla.getRowCount(); j++) {
                        if ((int) datosDevueltaTabla.getValueAt(j, 2) == denominacion) {
                            int cantidadExistente = (int) datosDevueltaTabla.getValueAt(j, 0);
                            datosDevueltaTabla.setValueAt(cantidadExistente + 1, j, 0);
                            encontrado = true;
                            break;
                        }
                    }

                    if (!encontrado) {
                        datosDevueltaTabla.addRow(new Object[] { 1, tipo, denominacion });
                    }

                    acumulador += denominacion;
                    CantidadBilletes[i]--;
                    EntregoCambio = true;
                }
            }

            if (!EntregoCambio) {
                JOptionPane.showMessageDialog(null, "No hay existencia de billetes o monedas disponibles.");
            } else if (acumulador < cantidad) {
                JOptionPane.showMessageDialog(null, "No hay suficiente existencia para completar el valor.");
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error en la entrada de datos.");
        }
    }

}
