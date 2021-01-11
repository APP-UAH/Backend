import com.appuah.Factories.ReservationFactory
import com.appuah.UserAbstractFactory.AbstractFactory

fun main(args: Array<String>) {

    val factoria = ReservationFactory.getReservation("Library")
    val reserva = factoria?.createReservation(1, false, "hoy", "mañana")
    println(reserva.toString())
    println(reserva?.id)
    reserva?.id = 2
    println(reserva?.id)

    val factoriaAbstracta = AbstractFactory.getFactory("Student")
    val user = factoriaAbstracta?.createInternalUser("Charly", "EsGay", "Student")
    print(user.toString())

}