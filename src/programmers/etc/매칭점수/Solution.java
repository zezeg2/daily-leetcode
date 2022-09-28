package programmers.etc.매칭점수;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Solution {
    static int solution(String word, String[] pages) {
        int answer = 0;
        List<Page> pageList= new ArrayList<>();
        double maxScore = 0;
        for (String p : pages) {
            Page page = new Page(p);
            page.setDefaultScore(word);
            pageList.add(page);
        }
        for (int i = 0; i < pageList.size(); i++){
            Page page = pageList.get(i);
            page.setInLinkScore(pageList);
            double prevMax = maxScore;
            maxScore = Double.max(page.defaultScore + page.inLinkScore, maxScore);
            if (prevMax != maxScore) answer = i;
        }
        return answer;
    }
    static class Page{
        Pattern urlPattern = Pattern.compile("<meta property=\"og:url\" content=\"(\\S*)\"");
        Pattern linkPattern = Pattern.compile("<a href=\"https://(\\S*)\"");
        Matcher urlMatcher, linkMatcher;
        String content;
        String url;
        double defaultScore, inLinkScore, outLinkScore;
        List<String> outLinkList = new ArrayList<>();

        public Page(String content) {
            this.content = content;
            urlMatcher = urlPattern.matcher(content);
            urlMatcher.find();
            this.url = urlMatcher.group().split("=")[2].replaceAll("\"", "");

            linkMatcher = linkPattern.matcher(content);
            while (linkMatcher.find()) this.outLinkList.add(linkMatcher.group().split("\"")[1]);
            this.outLinkScore = outLinkList.size();
        }

        public void setDefaultScore(String word) {
            this.defaultScore = 0;
            Pattern wordPattern = Pattern.compile("\\b(?i)"+word+"\\b");
            Matcher wordMatcher;
            String body = content.split("<body>")[1].split("</body>")[0].replaceAll("[0-9]", " ");
            wordMatcher = wordPattern.matcher(body);
            while (wordMatcher.find()) this.defaultScore++;
        }

        public void setInLinkScore(List<Page> pageList) {
            inLinkScore =  pageList.stream().filter(p -> p.outLinkList.contains(this.url)).mapToDouble(p -> p.defaultScore / p.outLinkScore).sum();
        }
    }

    static void transBracket() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String val = reader.readLine();  // Hi Anna
        val = val.replaceAll("]", "}").replaceAll("\\[", "{");
        System.out.println(val);
    }

    public static void main(String[] args) throws IOException {
        String word = "Muzi";
        String[] pages = {"<html lang=\"ko\" xml:lang=\"ko\" xmlns=\"http://www.w3.org/1999/xhtml\">\n<head>\n  <meta charset=\"utf-8\">\n  <meta property=\"og:url\" content=\"https://careers.kakao.com/interview/list\"/>\n</head>  \n<body>\n<a href=\"https://programmers.co.kr/learn/courses/4673\"></a>#!MuziMuzi!)jayg07con&&\n\n</body>\n</html>", "<html lang=\"ko\" xml:lang=\"ko\" xmlns=\"http://www.w3.org/1999/xhtml\">\n<head>\n  <meta charset=\"utf-8\">\n  <meta property=\"og:url\" content=\"https://www.kakaocorp.com\"/>\n</head>  \n<body>\ncon%\tmuzI92apeach&2<a href=\"https://hashcode.co.kr/tos\"></a>\n\n\t^\n</body>\n</html>"};
        System.out.println(solution(word, pages));

    }
}
