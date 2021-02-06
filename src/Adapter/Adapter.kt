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

    fun adaptStateToBoolean(ReservationState : ReservationState?) : Boolean?{
        //Null return StateNotProcessed
        //True StateAccepted
        //False StateNotAccepted
        return (if (ReservationState!!.state.toLowerCase().equals("accepted")) true else if (ReservationState!!.state.toLowerCase().equals("not accepted")) false else null)
    }
}