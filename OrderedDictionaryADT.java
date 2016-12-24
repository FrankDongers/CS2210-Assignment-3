public interface OrderedDictionaryADT {
    /* Ordered Dictionary ADT */

    /* Returns the Record object with key k, or it returns null if such 
       a record is not in the dictionary. */
    public Record find (Key k);

    /* Inserts r into the ordered dictionary. It throws a DictionaryException 
       if a record with the same key as r is already in the dictionary. */
    public void insert (Record r) throws DictionaryException;

    /*  Removes the record with Key k from the dictionary. It throws a 
        DictionaryException if the record is not in the dictionary. */
    public void remove (Key k) throws DictionaryException;

    /* Returns the successor of k (the record from the ordered dictionary 
       with smallest key larger than k); it returns null if the given key has
       no successor. The given key DOES NOT need to be in the dictionary. */
    public Record successor (Key k);

    /* Returns the predecessor of k (the record from the ordered dictionary 
       with largest key smaller than k; it returns null if the given key has 
       no predecessor. The given key DOES NOT need to be in the dictionary.  */
    public Record predecessor (Key k);

    /* Returns the record with smallest key in the ordered dictionary. 
       Returns null if the dictionary is empty.  */
    public Record smallest ();

    /* Returns the record with largest key in the ordered dictionary. 
       Returns null if the dictionary is empty.  */
    public Record largest ();
}
		


