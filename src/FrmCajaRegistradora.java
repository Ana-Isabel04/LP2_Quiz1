
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
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;

public class FrmCajaRegistradora extends JFrame {
    JTable tblTablaDevolucion;
    JComboBox cmbOpciones;
    JTextField txtCantidad;
    JTextField txtCantDevolucion;

    Integer[] opciones = new Integer[] { 100000, 50000, 20000, 10000, 5000, 2000, 1000, 500, 200, 100, 50 };
    String[] encabezados = new String[] { "Cantidad", "Presentación", "Denominción" };

    // metodo constructor
    public FrmCajaRegistradora() {
        setSize(500, 400);
        setTitle("Caja Registradora");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        JLabel LabelDenomimacion = new JLabel("Denominación");
        LabelDenomimacion.setBounds(10, 10, 250, 50);
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

        // falta poner encabezado de la tabla
        DefaultTableModel datosDevueltaTabla = new DefaultTableModel(null, encabezados);
        tblTablaDevolucion.setModel(datosDevueltaTabla);

        JTextArea txtSaldo = new JTextArea("Tienes un saldo a favor");
        txtSaldo.setBounds(10, 350, 250, 50);
        txtSaldo.setEditable(false);
        txtSaldo.setOpaque(false);
        txtSaldo.setLineWrap(true);
        getContentPane().add(txtSaldo);

        JTextField txtValorSaldo = new JTextField();
        txtValorSaldo.setBounds(150, 350, 100, 25);
        txtValorSaldo.setEnabled(false);
        getContentPane().add(txtValorSaldo);

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
        String Denominaciones =  String.valueOf(cmbOpciones.getSelectedIndex()); 
        for (int i=1;i<opciones.length;i++){
            try{
            if (opciones[i]==Denominaciones){
                int dato = Integer.parseInt(txtCantidad.getText());
                CantidadBilletes[i]=dato;
                }
            }catch(Exception ex) {
                JOptionPane.showMessageDialog(null, "Debe digitar un valor numérico");

            }
   }

    private void Devolucion() {
       
    }
}
