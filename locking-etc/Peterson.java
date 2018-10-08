class PetersonLock {  // attempt 3
  private boolean[] flag = new boolean[2];
  private int yielder;  // again shared, do so safely
  public void lock() {
    int i = ThreadID.get();
    int j = 1 - i;
    flag[i] = true;
    yielder =  i;  // let the other go first
    while (flag[j] && yielder == i);
  }
  public void unlock() {
    int i = ThreadID.get();
    flag[i] = false;
  }
}
