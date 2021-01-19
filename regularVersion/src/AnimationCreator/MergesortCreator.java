package AnimationCreator;

import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import model.IAnimation;
import model.SimpleAnimation;
import motion.Motion;

/**
 * Create a mergesort visualization animation.
 */
public class MergesortCreator {

  private IAnimation model = new SimpleAnimation();
  private int[] arr;
  private final int canvas = 800, margin = 50, width = 40, gap = 10;
  private final int compare = 20, pull = 10, settle = 10;
  private List<List<String>> log = new ArrayList<>();
  private int timer = 0;

  /**
   * Constructs a mergesort visualization.
   *
   * @param arr an array of integers to be sorted
   */
  public MergesortCreator(int[] arr) {
    this.arr = arr;
  }

  public static void main(String[] args) throws IOException {
    int[] arr = {3, 44, 38, 5, 47, 15, 36, 26, 27, 2, 46, 4, 19, 50, 48};
    int[] temp = new int[arr.length];
    MergesortCreator creator = new MergesortCreator(arr);
    creator.mergesort(arr, 0, arr.length - 1, temp);
    creator.createAnimation();
  }

  private void mergesort(int[] arr, int low, int high, int[] temp) {
    if (low < high) {
      int mid = (low + high) / 2;
      mergesort(arr, low, mid, temp);
      mergesort(arr, mid + 1, high, temp);
      merge(arr, low, mid, high, temp);
    }
  }

  private void merge(int[] arr, int low, int mid, int high, int[] temp) {
    int i = 0, left = low, right = mid + 1;
    while (left <= mid && right <= high) {
      log.add(new ArrayList<>(
          Arrays.asList("compare", String.valueOf(arr[left]), String.valueOf(arr[right]))));
      if (arr[left] < arr[right]) {
        log.add(new ArrayList<>(Arrays.asList("pull", String.valueOf(arr[left]))));
        temp[i] = arr[left];
        left++;
      } else {
        log.add(new ArrayList<>(Arrays.asList("pull", String.valueOf(arr[right]))));
        temp[i] = arr[right];
        right++;
      }
      i++;
    }
    while (left <= mid) {
      log.add(new ArrayList<>(Arrays.asList("pull", String.valueOf(arr[left]))));
      temp[i] = arr[left];
      i++;
      left++;
    }

    while (right <= high) {
      log.add(new ArrayList<>(Arrays.asList("pull", String.valueOf(arr[right]))));
      temp[i] = arr[right];
      i++;
      right++;
    }

    for (int j = 0; j < i; j++) {
      log.add(new ArrayList<>(
          Arrays.asList("settle", String.valueOf(temp[j]), String.valueOf(low + j))));
      arr[low + j] = temp[j];
    }
  }

  private void createAnimation() throws IOException {
    setUp();
    makeMove();
    Appendable out = new PrintStream("mergesort.txt");
    out.append(toString());
  }

  private void setUp() {
    int i = 0;
    model.initialCanvas(0, 0, canvas, canvas);
    for (int num : arr) {
      model.addShape(String.valueOf(num), "rectangle");
      model
          .addMotion(String.valueOf(num), 0, margin + i * (width + gap), canvas / 2, width, num * 4,
              0, 0, 0);
      i++;
    }
  }

  private void makeMove() {
    for (List<String> move : log) {
      String cmd = move.get(0);
      if (model.getMotionsFor(move.get(1)).get(model.getMotionsFor(move.get(1)).size() - 1)
          .getTick() != 0) {
        model.addMotionFreeze(move.get(1), this.timer);
      }
      switch (cmd) {
        case "compare":
          if (model.getMotionsFor(move.get(2)).get(model.getMotionsFor(move.get(2)).size() - 1)
              .getTick() != 0) {
            model.addMotionFreeze(move.get(2), this.timer);
          }
          compare(move.get(1), move.get(2));
          break;
        case "pull":
          pull(move.get(1));
          break;
        case "settle":
          settle(move.get(1), Integer.parseInt(move.get(2)));
          break;
        default:
          throw new IllegalArgumentException("no such command.");
      }
    }
  }

  private void compare(String s1, String s2) {
    model.addMotionFreeze(s1, this.timer + 1);
    model.addMotionFreeze(s2, this.timer + 1);

    model.addMotionColor(s1, this.timer + compare / 2, 200, 200, 200);
    model.addMotionColor(s1, this.timer + compare, 0, 0, 0);
    model.addMotionColor(s2, this.timer + compare / 2, 200, 200, 200);
    model.addMotionColor(s2, this.timer + compare, 0, 0, 0);
    this.timer += compare;
  }

  private void pull(String s) {
    model.addMotionFreeze(s, this.timer + 1);
    model.addMotionPosition(s, this.timer + pull,
        model.getMotionsFor(s).get(model.getMotionsFor(s).size() - 1).getX(), canvas / 4);
    this.timer += pull;
  }

  private void settle(String s, int lot) {
    model.addMotionFreeze(s, this.timer + 1);
    model.addMotionPosition(s, this.timer + settle, margin + lot * (width + gap), canvas / 2);
    this.timer += settle;
  }

  @Override
  public String toString() {
    StringBuilder ans = new StringBuilder(
        "canvas " + model.getCanvasTopLeftX() + " " + model.getCanvasTopLeftY() + " " + model
            .getCanvasWidth() + " " + model.getCanvasHeight() + "\n");
    for (String name : model.getMotions().keySet()) {
      if (model.getMotionsFor(name).size() < 2) {
        continue;
      }
      String temp =
          "shape " + name + " " + model.getShapeFor(name).getType() + "\n" + motionToString(name);
      ans.append(temp);
    }
    return ans.toString();
  }

  private String motionToString(String name) {
    StringBuilder ans = new StringBuilder();
    for (int i = 1; i < model.getMotionsFor(name).size(); i++) {
      Motion lastInterval = model.getMotionsFor(name).get(i - 1);
      Motion thisInterval = model.getMotionsFor(name).get(i);
      String temp =
          "motion " + name + " " + lastInterval.toString() + " " + thisInterval.toString() + "\n";
      ans.append(temp);
    }
    return ans.toString();
  }


}

