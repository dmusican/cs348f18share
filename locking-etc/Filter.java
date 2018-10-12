class Filter {
    public static final int n = 10; // or whatever
  private int[] level = new int[n];
  private int[] yielder = new int[n];

  public void lock() {
    int me = ThreadID.get();
    // Note that level[me] is always 0 here
    for (int i = 1; i < n; i++) {   // attempt successive levels
      level[me] = i;                // thread goes to next level
      yielder[i] = me;              // yielder at this level is me

      // Don't advance to next level if both are true:
      // 1. I'm yielding
      // 2. There's at least one more thread at my level or further in
      boolean waiting = true;
      while (waiting) {
          waiting = false;     // can I go? please?
          for (k=0; k < n; k++) {   //  && !waiting
                if (k != me && yielder[i] == me && level[k] >= i ) {
                   waiting = true;
                }
          }
      }
    }
  }

  public void unlock() {
    int me = ThreadID.get();
    level[me] = 0;
  }
}
