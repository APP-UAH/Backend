package Adapter

import State.ReservationState
import State.StateAccepted
import State.StateNotAccepted
import State.StateNotProcessed

class Adapter {

    fun adaptBooleanToState(b : Boolean?) : ReservationState{
        //Null return StateNotProcessed
        //True StateAccepted
        //False StateNotAccepted
        return (if (b == null) StateNotProcessed() else if (b) StateAccepted() else StateNotAccepted())
    }

    fun adaptStateToBoolean(reservationState: ReservationState?) : Boolean?{
        //Null return StateNotProcessed
        //True StateAccepted
        //False StateNotAccepted
        return (if (reservationState!!.state.equals("Accepted")) true else if (reservationState.state.equals("Not accepted")) false else null)
    }

}