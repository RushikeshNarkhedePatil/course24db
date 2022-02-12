package com.example.dancedb;

public class Dance{
    int _id;
    String _name;
    String _mobno;
    String _dancetype;

    public Dance() {

    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String get_name() {
        return _name;
    }

    public void set_name(String _name) {
        this._name = _name;
    }

    public String get_mobno() {
        return _mobno;
    }

    public void set_mobno(String _mobno) {
        this._mobno = _mobno;
    }

    public String get_dancetype() {
        return _dancetype;
    }

    public void set_dancetype(String _dancetype) {
        this._dancetype = _dancetype;
    }

    public Dance(int _id, String _name, String _mobno, String _dancetype) {
    this._id=_id;
    this._name=_name;
    this._mobno=_mobno;
    this._dancetype=_dancetype;
    }

}
