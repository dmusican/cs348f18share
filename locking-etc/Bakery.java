class Bakery {
  public static final int n = 10;   // or whatever
  private boolean[] flag = new boolean[n];
  private long[] label = new long[n];

  public void lock() {
    int i = ThreadID.get();
    flag[i] = true;
    label[i] = max(label[...]) + 1;    // max does obvious
    boolean waiting = true;
    while (waiting) {
        waiting = false;     // can I go? please?
        for (k=0; k < n; k++) {    // && !waiting if you like
              if (k != i && flag[k] && (label[k], k) << (label[i],i)) {
                  // pseudocode tuple <
                 waiting = true;
              }
        }
    }
  }

  public void unlock() {
    flag[ThreadID.get()] = false;
  }
}
