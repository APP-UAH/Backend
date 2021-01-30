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

    fun adaptStateToBoolean(state : ReservationState?) : Boolean?{
        //Null return StateNotProcessed
        //True StateAccepted
        //False StateNotAccepted
        return (if (state!!.equals("Accepted")) true else if (state!!.equals("Not Accepted")) false else null)
    }

}