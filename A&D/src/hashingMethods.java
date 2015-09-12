/**
 * Created by Niek on 9/10/2015.
 */
public class hashingMethods {

    public static void main(String args[]) {

    }


    /**
         * @param message
         *            of length n
         * @param size
         *            maximum size of the return value
         * @return H
         */
        public int hash1(String message, int size) {
            int hash = 1;
            for (int i = 0; i < size; i++) {
                hash = message.charAt(i) * ((hash % 257) + 1);
            }
            return hash % size;
        }

        /**
         * @param message
         *            of length n
         * @param size
         *            maximum size of the return value
         * @return H
         */
        public int hash2(String message, int size) {
            int hash = 0;
            for (int i = 0; i < size; i++) {
                hash = 4 * hash + message.charAt(i);
            }
            return (hash & 0x7FFFFFFF) % size;
        }

         public int hash3(String message, int size) {
            int hash = size;
             for (int i = 0; i < size; i++) {
                 hash = hash*613 + message.charAt(i);
             }
             return (hash & 0x3FFFFFFF) % size;
         }
}
