// Approach
// We build a Trie from the input words, marking isEnd at the end of each word. 
// Then we perform a DFS traversal from the root, only visiting child nodes where isEnd == true, 
// which ensures we're only building valid words one character at a time. 
// During traversal, we keep track of the longest valid word, updating the result if a longer or lexicographically smaller valid word is found.

// Time Complexity
// Insert all words into Trie:
// O(N × L) where N = number of words and L = average word length.

// DFS traversal of Trie:
// Maximum O(26^L) in worst-case Trie depth (if all combinations exist),
// but effectively O(N × L) because we only traverse existing nodes built from the words.

// Overall: O(N × L)

// Space Complexity
// Trie storage:
// O(N × L) for storing all characters in the Trie.
// Auxiliary space:
// O(L) for the StringBuilder path during DFS recursion.
// O(1) for the final StringBuilder result (constant-sized result).
// Overall: O(N × L)

class Solution {
    class TrieNode{
        boolean isEnd;
        TrieNode []children;

        TrieNode(){
            this.children = new TrieNode[26];
        }
    }

    private void insert(String word){
        TrieNode curr = root;
        for(int i=0;i<word.length();++i){
            char x = word.charAt(i);
            if(curr.children[x-'a'] == null) 
                curr.children[x-'a'] = new TrieNode();
            curr = curr.children[x-'a'];
        }
        curr.isEnd = true;
    }
    TrieNode root;
    public String longestWord(String[] words) {
        this.root = new TrieNode();
        for(String word : words)
            insert(word);
        StringBuilder result = new StringBuilder();
        dfs(root, new StringBuilder(), result);
        return result.toString();
    }

    private void dfs(TrieNode node, StringBuilder path, StringBuilder result){
        // base case
        if(path.length() > result.length()){
            result.setLength(0);
            result.append(path);
        }
        if(path.length() == result.length() && path.toString().compareTo(result.toString())< 0){
            result.setLength(0);
            result.append(path);
        }
        // logic
        for(char c = 'a';c <= 'z' ; ++c){
            TrieNode next = node.children[c-'a'];
            if(next != null && next.isEnd){
                // action
                path.append(c);
                // recurse
                dfs(next,path,result);
                // backtrack
                path.deleteCharAt(path.length()-1);
            }
        }
    }
}
