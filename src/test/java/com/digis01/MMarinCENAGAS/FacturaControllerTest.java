package com.digis01.MMarinCENAGAS;

import com.digis01.MMarinCENAGAS.Controller.FacturaRestController;
import com.digis01.MMarinCENAGAS.DAO.IContratoDAO;
import com.digis01.MMarinCENAGAS.DAO.IFacturaDAO;
import com.digis01.MMarinCENAGAS.DAO.INodoEntregaDAO;
import com.digis01.MMarinCENAGAS.DAO.INodoRecepcionDAO;
import com.digis01.MMarinCENAGAS.DAO.IUsuarioDAO;
import com.digis01.MMarinCENAGAS.JPA.Contrato;
import com.digis01.MMarinCENAGAS.JPA.Factura;
import com.digis01.MMarinCENAGAS.JPA.NodoEntrega;
import com.digis01.MMarinCENAGAS.JPA.NodoRecepcion;
import com.digis01.MMarinCENAGAS.JPA.Usuario;
import com.digis01.MMarinCENAGAS.Service.ContratoService;
import com.digis01.MMarinCENAGAS.Service.FacturaService;
import com.digis01.MMarinCENAGAS.Service.NodoEntregaService;
import com.digis01.MMarinCENAGAS.Service.NodoRecepcionService;
import com.digis01.MMarinCENAGAS.Service.UsuarioService;
import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(FacturaRestController.class)
public class FacturaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private IContratoDAO iContratoDAO;

    @InjectMocks
    private ContratoService contratoService;
    
    @Mock
    private IUsuarioDAO iUsuarioDAO;
    
    @InjectMocks
    private UsuarioService usuarioService;
    
    @Mock
    private IFacturaDAO iFacturaDAO;
    
    @InjectMocks
    private FacturaService facturaService;
    
    @Mock
    private INodoRecepcionDAO iNodoRecepcionDAO; 
    
    @InjectMocks
    private NodoRecepcionService nodoRecepcionService;
    
    @Mock
    private INodoEntregaDAO iNodoEntregaDAO;
    
    @InjectMocks
    private NodoEntregaService nodoEntregaDAO;
    
    @Test
    public void ProcesarArchivoContratoNull() {
        
        
    }
    
    public List<Factura> LeerArchiv(File archivo){
        List<Factura> facturas = new ArrayList<Factura>();
        Factura factura1 = new Factura();
        factura1.Contrato = new Contrato();
        factura1.Contrato.Usuario = new Usuario();
        factura1.Contrato.NodoEntrega = new NodoEntrega();
        factura1.Contrato.NodoRecepcion = new NodoRecepcion();
        factura1.setFecha(new Date("2000-12-31"));
        factura1.Contrato.setNombre("CENAGAS/A/500/18");
        factura1.Contrato.Usuario.setNombre("Comisión Federal de Electricidad");
        factura1.setCantidadAsignadaEntrega(0);
        factura1.setCantidadAsignadaRecepcion(7888);
        factura1.setCantidadNominadaEntrega(3293.377);
        factura1.setCantidadNominadaRecepcion(0);
        factura1.Contrato.NodoRecepcion.setCodigo("V045");
        factura1.Contrato.NodoRecepcion.setDescripcion("PEPMENDOZA");
        factura1.Contrato.NodoEntrega.setCodigo("E168");
        factura1.Contrato.NodoEntrega.setDescripcion("CFESALAMANCACOG");
        Factura factura2 = new Factura();
        factura2.Contrato = new Contrato();
        factura2.Contrato.Usuario = new Usuario();
        factura2.Contrato.NodoEntrega = new NodoEntrega();
        factura2.Contrato.NodoRecepcion = new NodoRecepcion();
        factura2.setFecha(new Date("2000-12-31"));
        factura2.Contrato.setNombre("CENAGAS/A/500/18");
        factura2.Contrato.Usuario.setNombre("Pemex Transformación Industrial");
        factura2.setCantidadAsignadaEntrega(0);
        factura2.setCantidadAsignadaRecepcion(0);
        factura2.setCantidadNominadaEntrega(2568.967);
        factura2.setCantidadNominadaRecepcion(0);
        factura2.Contrato.NodoRecepcion.setCodigo("V025");
        factura2.Contrato.NodoRecepcion.setDescripcion("CACTUSNVOPMX");
        factura2.Contrato.NodoEntrega.setCodigo("E168");
        factura2.Contrato.NodoEntrega.setDescripcion("AGUADULCE");       
        facturas.add(factura1);
        facturas.add(factura2);       
        return facturas;
    }
}
