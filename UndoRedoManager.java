public class UndoRedoManager<T> {
    private class Node {
        private T state;
        private Node prev;
        private Node next;
        private Node(T state) {
            this.state = state;
        }
    }

    private Node currentState;

    // Undo method which moves the current state to the previous state
    public T undo() {
        if (currentState == null) {
            System.out.println("No state to undo");
            return null;
        }

        Node previousState = currentState.prev;
        if (previousState == null) {
            System.out.println("Reached the initial state");
            return null;
        } else {

            currentState = previousState;
        }
        return currentState.state;
    }

    // Redo method which moves the current state to the next state
    public T redo() {
        if (currentState == null || currentState.next == null) {
            System.out.println("No state to redo");
            return null;
        }
        currentState = currentState.next;
        return currentState.state;
    }

    public void addState(T newState) {
        Node newNode = new Node(newState);
        newNode.prev = currentState;
        newNode.next = null;

        if (currentState != null) {
            currentState.next = newNode;
        }
        currentState = newNode;
    }

    //The main method demonstrates both undo and redo operations
    public static void main(String[] args) {
        UndoRedoManager<String> undoRedoManager = new UndoRedoManager<>();
        undoRedoManager.addState("State 1");
        undoRedoManager.addState("State 2");
        undoRedoManager.addState("State 3");

        System.out.println("Current State: " + undoRedoManager.currentState.state);
        
        undoRedoManager.undo();
        System.out.println("After undo: " + undoRedoManager.currentState.state);
        
        undoRedoManager.undo();
        System.out.println("After undo: " + undoRedoManager.currentState.state);
        
        undoRedoManager.redo();
        System.out.println("After redo: " + undoRedoManager.currentState.state);
        
        undoRedoManager.redo();
        System.out.println("After redo: " + undoRedoManager.currentState.state);
        
        undoRedoManager.redo();
        
        undoRedoManager.undo();
        undoRedoManager.undo();
        undoRedoManager.undo();
    }
}