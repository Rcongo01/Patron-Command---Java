import java.util.Scanner;

// Paso 1: Interfaz Command
interface Command {
    void ejecutar();
}

// Paso 2: Clase de comando concreta
class RetirarDinero implements Command {
    private CajeroAutomatico cajero;
    private double monto;

    public RetirarDinero(CajeroAutomatico cajero, double monto) {
        this.cajero = cajero;
        this.monto = monto;
    }

    public void ejecutar() {
        cajero.retirarDinero(monto);
    }
}

// Paso 3: Clase Receptor (CajeroAutomatico)
class CajeroAutomatico {
    private double saldo;

    public CajeroAutomatico(double saldoInicial) {
        this.saldo = saldoInicial;
    }

    public void retirarDinero(double monto) {
        if (saldo >= monto) {
            saldo -= monto;
            System.out.println("Retiraste $" + monto);
            System.out.println("Saldo restante: $" + saldo);
        } else {
            System.out.println("Error: Saldo insuficiente");
        }
    }
}

// Paso 4: Clase Invoker (TerminalCajero)
class TerminalCajero {
    private Command comando;

    public void setComando(Command comando) {
        this.comando = comando;
    }

    public void ejecutarComando() {
        comando.ejecutar();
    }
}

// Clase principal (AplicacionCajero)
public class AplicacionCajero {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CajeroAutomatico cajero = new CajeroAutomatico(10000.0);
        TerminalCajero terminal = new TerminalCajero();

        System.out.println("Bienvenido al Cajero Automático");
        System.out.print("Ingrese el monto que desea retirar: ");
        double montoRetiro = scanner.nextDouble();

        Command retirarDinero = new RetirarDinero(cajero, montoRetiro);
        terminal.setComando(retirarDinero);
        terminal.ejecutarComando();

        scanner.close();
    }
}
