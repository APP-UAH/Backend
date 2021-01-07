package com.appuah.ReservationEntities

class ReservationLibrary(): Reservation {
    override var id: Int
        get() {
            return this.id
        }
        set(value) {
            this.id = value
        }

    override var accepted: Boolean
        get() {
            return this.accepted
        }
        set(value) {
            this.accepted = value
        }

    override var begin: String
        get() {
            return this.begin
        }
        set(value) {
            this.begin = value
        }

    override var end: String
        get() {
            return this.end
        }
        set(value) {
            this.end = value
        }

    override fun isAccepted(): Boolean {
        return accepted
    }
}