package com.example.tuffy_josh.termproject;

/**
 * Created by OPPENHE_THOM on 4/24/2017.
 */

public class Schedule {

    public ClassBlock CB1 = null;
    public ClassBlock CB2 = null;
    public ClassBlock CB3 = null;
    public ClassBlock CB4 = null;
    public ClassBlock CB5 = null;
    public ClassBlock CB6 = null;
    public ClassBlock CB7 = null;
    public ClassBlock CB8 = null;
    public ClassBlock CB9 = null;
    public ClassBlock CB10 = null;
    public ClassBlock CB11 = null;
    public ClassBlock CB12 = null;
    public ClassBlock CB13 = null;
    public ClassBlock CB14 = null;
    public ClassBlock CB15 = null;
    public ClassBlock CB16 = null;
    public ClassBlock CB17 = null;
    public ClassBlock CB18 = null;
    public ClassBlock CB19 = null;
    public ClassBlock CB20 = null;

    public class ClassBlock {

        private String className;
        private String section;
        private int blockTime;

        public ClassBlock(String name, String section, int block) {
            setClassName(name);
            setSection(section);
            setBlock(block);
        }

        public void setClassName(String in) {
            this.className = in;
        }

        public void setSection(String in) {
            this.section = in;
        }

        public void setBlock(int in) {
            this.blockTime = in;
        }

        public String getClassName() {
            return this.className;
        }

        public String getsection() {
            return this.section;
        }

        public int getBlock() {
            return this.blockTime;
        }

        public String toString() {
            return getClassName() + " " + getsection();
        }



    }


}
