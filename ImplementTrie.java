// Time Complexity (for all operations)
// insert, search, startsWith: O(L) where L is the length of the word/prefix.

// Space Complexity
// O(N × 26) where N is the total characters inserted — because each node has a fixed array of 26 pointers (for lowercase 'a' to 'z').


class Trie {
    class TrieNode{
        boolean isEnd;
        TrieNode []children;

        TrieNode(){
            this.children = new TrieNode[26];
        }
    }
    private TrieNode root;
    public Trie() {
        this.root = new TrieNode();
    }
    
    public void insert(String word) {
        TrieNode curr = root;
        for(int i =0;i<word.length();++i){
            char x = word.charAt(i);
            if(curr.children[x -'a'] == null){
                curr.children[x-'a'] = new TrieNode();
            }
            curr = curr.children[x-'a']; 
        }
        curr.isEnd = true;
    }
    
    public boolean search(String word) {
        TrieNode curr = root;
        for(int i =0;i<word.length();++i){
            char x = word.charAt(i);
            if(curr.children[x -'a'] == null){
                return false;
            }
            curr = curr.children[x-'a']; 
        }
        return curr.isEnd;
    }
    
    public boolean startsWith(String prefix) {
        TrieNode curr = root;
        for(int i =0;i<prefix.length();++i){
            char x = prefix.charAt(i);
            if(curr.children[x -'a'] == null){
                return false;
            }
            curr = curr.children[x-'a'];
        }
        return true;
    }
}

/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */
