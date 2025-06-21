// Time:
// Building Trie: O(D × L) — D = dictionary size, L = average root length
// Replacing words: O(N × M) — N = number of words in sentence, M = average word length

// Space:
// O(K) for Trie where K = total characters in all dictionary words
// O(N × M) for storing words and building result

class Solution {
    class TrieNode{
        boolean isEnd;
        TrieNode []children;

        TrieNode(){
            this.children = new TrieNode[26];
        }
    }
    private void insert(String word) {
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

    private TrieNode root;
    public String replaceWords(List<String> dictionary, String sentence) {
        root = new TrieNode();
        for(String word : dictionary){
            insert(word);
        }

        // Step 2: Split sentence into words
        String[] words = sentence.split(" ");

        StringBuilder result = new StringBuilder();
        for(String word : words ){
            TrieNode curr = root;
            StringBuilder replace = new StringBuilder();
            for(int i=0;i<word.length();++i){
                char ch = word.charAt(i);
                if(curr.children[ch -'a'] == null || curr.isEnd){
                    break;
                }
                replace.append(ch);
                curr = curr.children[ch-'a'];
            }
            if(curr.isEnd)
                result.append(replace);
            else
                result.append(word);
            result.append(' ');
        }

       return result.toString().trim();
    }
}
