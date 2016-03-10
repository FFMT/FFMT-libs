package fr.minecraftforgefrance.ffmtlibs.text3d;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.minecraft.client.model.ModelRenderer;

public class Model3DTextDefault extends Model3DTextBase
{
    private Map<Character, ModelRenderer[]> models = new HashMap<Character, ModelRenderer[]>();;
    public static final Model3DTextDefault instance = new Model3DTextDefault();

    private Model3DTextDefault()
    {
        this.models.put(' ', null);
        this.models.put('a', fromPattern("00000:00000:01110:00001:01111:10001:01111"));
        this.models.put('A', fromPattern("01110:10001:11111:10001:10001:10001:10001"));
        this.models.put('b', fromPattern("10000:10000:10110:11001:10001:10001:11110"));
        this.models.put('B', fromPattern("11110:10001:11110:10001:10001:10001:11110"));
        this.models.put('c', fromPattern("00000:00000:01110:10001:10000:10001:01110"));
        this.models.put('C', fromPattern("01110:10001:10000:10000:10000:10001:01110"));
        this.models.put('d', fromPattern("00001:00001:01101:10011:10001:10001:01111"));
        this.models.put('D', fromPattern("11110:10001:10001:10001:10001:10001:11110"));
        this.models.put('e', fromPattern("00000:00000:01110:10001:11111:10000:01111"));
        this.models.put('E', fromPattern("11111:10000:11100:10000:10000:10000:11111"));
        this.models.put('f', fromPattern("0011:0100:1111:0100:0100:0100:0100"));
        this.charSizes.put('f', 4.0F);
        this.models.put('F', fromPattern("11111:10000:11100:10000:10000:10000:10000"));
        this.models.put('g', fromPattern("00000:00000:00000:01111:10001:10001:01111:00001:11110"));
        this.models.put('G', fromPattern("01111:10000:10011:10001:10001:10001:01110"));
        this.models.put('h', fromPattern("10000:10000:10110:11001:10001:10001:10001"));
        this.models.put('H', fromPattern("10001:10001:11111:10001:10001:10001:10001"));
        this.models.put('i', fromPattern("1:0:1:1:1:1:1"));
        this.charSizes.put('i', 1.0F);
        this.models.put('I', fromPattern("111:010:010:010:010:010:111"));
        this.charSizes.put('I', 3.0F);
        this.models.put('j', fromPattern("00001:00000:00001:00001:00001:10001:01110"));
        this.models.put('J', fromPattern("00001:00001:00001:00001:00001:10001:01110"));
        this.models.put('k', fromPattern("1000:1000:1001:1010:1100:1010:1001"));
        this.charSizes.put('k', 4.0F);
        this.models.put('K', fromPattern("10001:10010:11100:10010:10001:10001:10001"));
        this.models.put('l', fromPattern("10:10:10:10:10:10:01"));
        this.charSizes.put('l', 2.0F);
        this.models.put('L', fromPattern("10000:10000:10000:10000:10000:10000:11111"));
        this.models.put('m', fromPattern("00000:00000:11010:10101:10101:10001:10001"));
        this.models.put('M', fromPattern("10001:11011:10101:10001:10001:10001:10001"));
        this.models.put('n', fromPattern("00000:00000:11110:10001:10001:10001:10001"));
        this.models.put('N', fromPattern("10001:11001:10101:10011:10001:10001:10001"));
        this.models.put('o', fromPattern("00000:00000:01110:10001:10001:10001:01110"));
        this.models.put('O', fromPattern("01110:10001:10001:10001:10001:10001:01110"));
        this.models.put('p', fromPattern("00000:00000:00000:10110:11001:10001:11110:10000:10000"));
        this.models.put('P', fromPattern("11110:10001:11110:10000:10000:10000:10000"));
        this.models.put('q', fromPattern("00000:00000:00000:01101:10011:10001:01111:00001:00001"));
        this.models.put('Q', fromPattern("01110:10001:10001:10001:10001:10010:01101"));
        this.models.put('r', fromPattern("00000:00000:10110:11001:10000:10000:10000"));
        this.models.put('R', fromPattern("11110:10001:11110:10001:10001:10001:10001"));
        this.models.put('s', fromPattern("00000:00000:01111:10000:01110:00001:11110"));
        this.models.put('S', fromPattern("01111:10000:01110:00001:00001:10001:01110"));
        this.models.put('t', fromPattern("010:010:111:010:010:010:001"));
        this.charSizes.put('t', 3.0F);
        this.models.put('T', fromPattern("11111:00100:00100:00100:00100:00100:00100"));
        this.models.put('u', fromPattern("00000:00000:10001:10001:10001:10001:01111"));
        this.models.put('U', fromPattern("10001:10001:10001:10001:10001:10001:01110"));
        this.models.put('v', fromPattern("00000:00000:10001:10001:10001:01010:00100"));
        this.models.put('V', fromPattern("10001:10001:10001:10001:01010:01010:00100"));
        this.models.put('w', fromPattern("00000:00000:10001:10001:10101:10101:01111"));
        this.models.put('W', fromPattern("10001:10001:10001:10001:10101:11011:10001"));
        this.models.put('x', fromPattern("00000:00000:10001:01010:00100:01010:10001"));
        this.models.put('X', fromPattern("10001:01010:00100:01010:10001:10001:10001"));
        this.models.put('y', fromPattern("00000:00000:00000:10001:10001:10001:01111:00001:11110"));
        this.models.put('Y', fromPattern("10001:01010:00100:00100:00100:00100:00100"));
        this.models.put('z', fromPattern("00000:00000:11111:00010:00100:01000:11111"));
        this.models.put('Z', fromPattern("11111:00001:00010:00100:01000:10000:11111"));
        
        this.models.put('0', fromPattern("01110:10001:10011:10101:11001:10001:01110"));
        this.models.put('1', fromPattern("00100:01100:00100:00100:00100:00100:11111"));
        this.models.put('2', fromPattern("01110:10001:00001:00110:01000:10001:11111"));
        this.models.put('3', fromPattern("01110:10001:00001:00110:00001:10001:01110"));
        this.models.put('4', fromPattern("00011:00101:01001:10001:11111:00001:00001"));
        this.models.put('5', fromPattern("11111:10000:11110:00001:00001:10001:01110"));
        this.models.put('6', fromPattern("00110:01000:10000:11110:10001:10001:01110"));
        this.models.put('7', fromPattern("11111:10001:00001:00010:00100:00100:00100"));
        this.models.put('8', fromPattern("01110:10001:10001:01110:10001:10001:01110"));
        this.models.put('9', fromPattern("01110:10001:10001:01111:00001:00010:01100"));
        
        this.models.put('à', fromPattern("11000:00000:01110:00001:01111:10001:01111"));
        this.models.put('â', fromPattern("01110:10001:01110:00001:01111:10001:01111"));
        this.models.put('ä', fromPattern("01010:00000:01110:00001:01111:10001:01111"));
        this.models.put('è', fromPattern("11000:00000:01110:10001:11111:10000:01111"));
        this.models.put('ê', fromPattern("01110:10001:01110:10001:11111:10000:01111"));
        this.models.put('é', fromPattern("00011:00000:01110:10001:11111:10000:01111"));
        this.models.put('ë', fromPattern("01010:00000:01110:10001:11111:10000:01111"));
        this.models.put('î', fromPattern("010:101:000:010:010:010:010"));
        this.charSizes.put('î', 3.0F);
        this.models.put('ï', fromPattern("000:101:000:010:010:010:010"));
        this.charSizes.put('ï', 3.0F);
        this.models.put('ô', fromPattern("01110:10001:01110:10001:10001:10001:01110"));
        this.models.put('ö', fromPattern("01010:00000:01110:10001:10001:10001:01110"));
        this.models.put('œ', fromPattern("000000:000000:010110:101001:101110:101000:010111"));
        this.models.put('ç', fromPattern("00000:00000:00000:00000:00000:01110:10001:10000:10001:01110:00001:00110:00011"));
        this.models.put('ù', fromPattern("11000:00000:10001:10001:10001:10001:01111"));
        this.models.put('µ', fromPattern("00000:00000:00000:00000:10001:10001:10001:10001:11111:10000:10000"));

        this.models.put('!', fromPattern("1:1:1:1:1:0:1"));
        this.charSizes.put('!', 1.0F);
        this.models.put('"', fromPattern("01010:01010:10100:00000:00000:00000:00000"));
        this.models.put('#', fromPattern("01010:01010:11111:01010:11111:01010:01010"));
        this.models.put('$', fromPattern("00100:01111:10000:01110:00001:11110:00100"));
        this.models.put('%', fromPattern("10001:10010:00010:00100:01000:01001:10001"));
        this.models.put('&', fromPattern("00100:01010:00100:01101:10110:10010:01101"));
        this.models.put('\'', fromPattern("01000:01000:10000:00000:00000:00000:00000"));
        this.models.put('(', fromPattern("00110:01000:10000:10000:10000:01000:00110"));
        this.models.put(')', fromPattern("01100:00010:00001:00001:00001:00010:01100"));
        this.models.put('*', fromPattern("000000:000000:010010:001100:010010:000000:000000"));
        this.models.put('+', fromPattern("00000:00100:00100:11111:00100:00100:00000"));
        this.models.put(',', fromPattern("0:0:0:0:0:0:1:1:1"));
        this.charSizes.put(',', 1.0F);
        this.models.put('-', fromPattern("00000:00000:00000:11111:00000:00000:00000"));
        this.models.put('/', fromPattern("00001:00010:00010:00100:01000:01000:10000"));
        this.models.put(':', fromPattern("0:1:1:0:1:1:0"));
        this.charSizes.put(':', 1.0F);
        this.models.put(';', fromPattern("0:1:1:0:1:1:1"));
        this.charSizes.put(';', 1.0F);
        this.models.put('<', fromPattern("0001:0010:0100:1000:0100:0010:0001"));
        this.models.put('=', fromPattern("00000:00000:11111:00000:00000:11111:00000"));
        this.models.put('>', fromPattern("1000:0100:0010:0001:0010:0100:1000"));
        this.models.put('?', fromPattern("01110:10001:00001:00010:00100:00000:00100"));
        this.models.put('@', fromPattern("011110:100001:101101:101101:101111:100000:011110"));
        this.models.put('[', fromPattern("111:100:100:100:100:100:111"));
        this.charSizes.put('[', 3.0F);
        this.models.put('\\', fromPattern("10000:01000:01000:00100:00010:00010:00001"));
        this.models.put(']', fromPattern("111:001:001:001:001:001:111"));
        this.charSizes.put(']', 3.0F);
        this.models.put('^', fromPattern("00100:01010:10001:00000:00000:00000:00000"));
        this.models.put('_', fromPattern("00000:00000:00000:00000:00000:00000:11111"));
        this.models.put('`', fromPattern("10:10:01:00:00:00:00"));
        this.charSizes.put('`', 2.0F);
        this.models.put('{', fromPattern("0011:0100:0100:1000:0100:0100:0011"));
        this.charSizes.put('{', 4.0F);
        this.models.put('|', fromPattern("1:1:1:1:1:1:1"));
        this.charSizes.put('|', 1.0F);
        this.models.put('}', fromPattern("1100:0010:0010:0001:0010:0010:1100"));
        this.charSizes.put('}', 1.0F);
        this.models.put('°', fromPattern("010:101:010:000:000:000:000"));
        this.charSizes.put('°', 3.0F);
        this.models.put('~', fromPattern("000000:000000:011001:100110:000000:000000"));
        this.charSizes.put('~', 6.0F);
        this.models.put('£', fromPattern("00110:01001:01000:11110:01000:01000:11111"));
        //this.models.put('', fromPattern("::::::"));
    }

    @Override
    public void renderChar(char ch, float scale, float x)
    {
        renderAll(this.models.get(ch), scale, x);
    }
    
    private ModelRenderer[] fromPattern(String pattern)
    {
        if(pattern != null && pattern.length() > 0 && pattern.contains(":"))
        {
            String[] lines = pattern.split(":");
            int lineLen = lines[0].length();
            for(String s : lines)
            {
                if(s.length() != lineLen)
                {
                    return null;
                }
            }

            char[][] tab = new char[lines.length][lineLen];
            for(int i = 0; i < lines.length; i++)
            {
                tab[i] = lines[i].toCharArray();
            }

            float xOff = ((float)lineLen) / -2.0F;
            float yOff = ((float)lines.length) / -2.0F;

            List<ModelRenderer> out = new ArrayList<ModelRenderer>();

            for(int lin = 0; lin < tab.length; lin++)
            {
                for(int col = 0; col < tab[lin].length; col++)
                {
                    if(tab[lin][col] == '1')
                    {
                        int x = 1;
                        while(col + x != tab[lin].length && tab[lin][col + x] == '1')
                        {
                            x++;
                        }

                        int y = 1;
                        while(lin + y != tab.length && tab[lin + y][col] == '1')
                        {
                            y++;
                        }

                        if(x > y)
                        {
                            out.add(new ModelRenderer(this, 0, 0).addBox(xOff + col, yOff + lin, -0.5F, x, 1, 1).setTextureSize(32, 32));
                            for(int xs = col; xs < col + x; xs++)
                            {
                                tab[lin][xs] = '0';
                            }
                        }
                        else
                        {
                            out.add(new ModelRenderer(this, 0, 0).addBox(xOff + col, yOff + lin, -0.5F, 1, y, 1).setTextureSize(32, 32));
                            for(int ys = lin; ys < lin + y; ys++)
                            {
                                tab[ys][col] = '0';
                            }
                        }
                    }
                }
            }

            ModelRenderer[] renders = new ModelRenderer[out.size()];
            for(int index = 0; index < out.size(); index++)
            {
                renders[index] = out.get(index);
            }
            return renders;
        }
        return null;
    }
}