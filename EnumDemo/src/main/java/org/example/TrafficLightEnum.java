package org.example;

public enum TrafficLightEnum {
    // litterals:
    RED(15) {
        @Override
        public void dummy() {
            System.out.println("doing silly thing as red");
        }
    },
    GREEN(13) {
        @Override
        public void dummy() {
            System.out.println("doing clever thing as green");
        }
    },
    ORANGE(2) {
        @Override
        public void dummy() {
            System.out.println("doing bad thing as red");
        }
    };

    // NB: nullable !
    // gifts: toString(), name(), ordinal(),
    // valueOf
    // equals() equivalent ==, hashCode()
    // compareTo()

    private int duration;

    TrafficLightEnum(int duration) {
        this.duration = duration;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    @Override
    public String toString() {
        return this.name().toLowerCase();
    }

    public abstract void dummy();
}
