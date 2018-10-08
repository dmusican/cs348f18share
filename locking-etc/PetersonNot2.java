class Lock {  // attempt 2
  private int yielder;  // again shared, do so safely
  public void lock() {
    int i = ThreadID.get();
    yielder =  i;  // let the other go first
    while (yielder == i);
  }
  public void unlock() {
  }
}
