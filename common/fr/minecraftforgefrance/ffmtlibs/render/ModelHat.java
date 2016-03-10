package fr.minecraftforgefrance.ffmtlibs.render;

import java.util.List;

import fr.minecraftforgefrance.ffmtlibs.event.ModelRenderEntry;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelHat extends ModelBase
{
    private List<String> str;
    private ModelRenderer[] model;
    private ModelBiped biped;

    public ModelHat(ModelBiped biped, List<String> str)
    {
        this.str = str;
        this.biped = biped;
        this.model = new ModelRenderer[str.size()];
        for(int i = 0; i < str.size(); i++)
        {

            String[] str2 = str.get(i).split(":");
            ModelRenderEntry entry = new ModelRenderEntry(Integer.valueOf(str2[0]), Integer.valueOf(str2[1]), Float.valueOf(str2[2]), Float.valueOf(str2[3]), Float.valueOf(str2[4]), Integer.valueOf(str2[5]), Integer.valueOf(str2[6]), Integer.valueOf(str2[7]), Float.valueOf(str2[8]), Float.valueOf(str2[9]), Float.valueOf(str2[10]), Integer.valueOf(str2[11]), Integer.valueOf(str2[12]), Boolean.valueOf(str2[13]), Boolean.valueOf(str2[14]), Integer.valueOf(str2[15]), Integer.valueOf(str2[16]), Float.valueOf(str2[17]), Integer.valueOf(str2[18]));

            this.textureWidth = entry.texture()[2];
            this.textureHeight = entry.texture()[3];
            this.model[i] = new ModelRenderer(this, entry.texture()[0], entry.texture()[1]);
            this.model[i].addBox(entry.getBox().posX(), entry.getBox().posY(), entry.getBox().posZ(), entry.getBox().dimX(), entry.getBox().dimY(), entry.getBox().dimZ());
            this.model[i].setRotationPoint(entry.rotation()[0], entry.rotation()[1], entry.rotation()[2]);
            this.model[i].setTextureSize(entry.texture()[2], entry.texture()[3]);
            this.model[i].mirror = entry.mirror();
        }
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
    {
        super.render(entity, f, f1, f2, f3, f4, f5);
        setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        for(int i = 0; i < this.model.length; i++)
        {
            this.model[i].render(f5);
        }
    }

    @Override
    public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity)
    {
        super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);

        for(int i = 0; i < this.str.size(); i++)
        {

            String[] str2 = this.str.get(i).split(":");
            ModelRenderEntry entry = new ModelRenderEntry(Integer.valueOf(str2[0]), Integer.valueOf(str2[1]), Float.valueOf(str2[2]), Float.valueOf(str2[3]), Float.valueOf(str2[4]), Integer.valueOf(str2[5]), Integer.valueOf(str2[6]), Integer.valueOf(str2[7]), Float.valueOf(str2[8]), Float.valueOf(str2[9]), Float.valueOf(str2[10]), Integer.valueOf(str2[11]), Integer.valueOf(str2[12]), Boolean.valueOf(str2[13]), Boolean.valueOf(str2[14]), Integer.valueOf(str2[15]), Integer.valueOf(str2[16]), Float.valueOf(str2[17]), Integer.valueOf(str2[18]));

            if(entry.getModelRota().followHead())
            {
                this.model[i].rotateAngleX = this.biped.bipedHead.rotateAngleX;
                this.model[i].rotateAngleY = this.biped.bipedHead.rotateAngleY;
            }
            else
            {
                switch(entry.getModelRota().var())
                {
                    case 1:
                    {
                        switch(entry.getModelRota().operation())
                        {
                            case 1:
                            {
                                switch(entry.getModelRota().axe())
                                {
                                    case 1:
                                    {
                                        this.model[i].rotateAngleX = f + entry.getModelRota().value();
                                        break;
                                    }
                                    case 2:
                                    {
                                        this.model[i].rotateAngleY = f + entry.getModelRota().value();
                                        break;
                                    }
                                    case 3:
                                    {
                                        this.model[i].rotateAngleZ = f + entry.getModelRota().value();
                                        break;
                                    }
                                }
                                break;
                            }
                            case 2:
                            {
                                switch(entry.getModelRota().axe())
                                {
                                    case 1:
                                    {
                                        this.model[i].rotateAngleX = f * entry.getModelRota().value();
                                        break;
                                    }
                                    case 2:
                                    {
                                        this.model[i].rotateAngleY = f * entry.getModelRota().value();
                                        break;
                                    }
                                    case 3:
                                    {
                                        this.model[i].rotateAngleZ = f * entry.getModelRota().value();
                                        break;
                                    }
                                }
                                break;
                            }
                        }
                        break;
                    }
                    case 2:
                    {
                        switch(entry.getModelRota().operation())
                        {
                            case 1:
                            {
                                switch(entry.getModelRota().axe())
                                {
                                    case 1:
                                    {
                                        this.model[i].rotateAngleX = f1 + entry.getModelRota().value();
                                        break;
                                    }
                                    case 2:
                                    {
                                        this.model[i].rotateAngleY = f1 + entry.getModelRota().value();
                                        break;
                                    }
                                    case 3:
                                    {
                                        this.model[i].rotateAngleZ = f1 + entry.getModelRota().value();
                                        break;
                                    }
                                }
                                break;
                            }
                            case 2:
                            {
                                switch(entry.getModelRota().axe())
                                {
                                    case 1:
                                    {
                                        this.model[i].rotateAngleX = f1 * entry.getModelRota().value();
                                        break;
                                    }
                                    case 2:
                                    {
                                        this.model[i].rotateAngleY = f1 * entry.getModelRota().value();
                                        break;
                                    }
                                    case 3:
                                    {
                                        this.model[i].rotateAngleZ = f1 * entry.getModelRota().value();
                                        break;
                                    }
                                }
                                break;
                            }
                        }
                        break;
                    }
                    case 3:
                    {
                        switch(entry.getModelRota().operation())
                        {
                            case 1:
                            {
                                switch(entry.getModelRota().axe())
                                {
                                    case 1:
                                    {
                                        this.model[i].rotateAngleX = f2 + entry.getModelRota().value();
                                        break;
                                    }
                                    case 2:
                                    {
                                        this.model[i].rotateAngleY = f2 + entry.getModelRota().value();
                                        break;
                                    }
                                    case 3:
                                    {
                                        this.model[i].rotateAngleZ = f2 + entry.getModelRota().value();
                                        break;
                                    }
                                }
                                break;
                            }
                            case 2:
                            {
                                switch(entry.getModelRota().axe())
                                {
                                    case 1:
                                    {
                                        this.model[i].rotateAngleX = f2 * entry.getModelRota().value();
                                        break;
                                    }
                                    case 2:
                                    {
                                        this.model[i].rotateAngleY = f2 * entry.getModelRota().value();
                                        break;
                                    }
                                    case 3:
                                    {
                                        this.model[i].rotateAngleZ = f2 * entry.getModelRota().value();
                                        break;
                                    }
                                }
                                break;
                            }
                        }
                        break;
                    }
                    case 4:
                    {
                        switch(entry.getModelRota().operation())
                        {
                            case 1:
                            {
                                switch(entry.getModelRota().axe())
                                {
                                    case 1:
                                    {
                                        this.model[i].rotateAngleX = f3 + entry.getModelRota().value();
                                        break;
                                    }
                                    case 2:
                                    {
                                        this.model[i].rotateAngleY = f3 + entry.getModelRota().value();
                                        break;
                                    }
                                    case 3:
                                    {
                                        this.model[i].rotateAngleZ = f3 + entry.getModelRota().value();
                                        break;
                                    }
                                }
                                break;
                            }
                            case 2:
                            {
                                switch(entry.getModelRota().axe())
                                {
                                    case 1:
                                    {
                                        this.model[i].rotateAngleX = f3 * entry.getModelRota().value();
                                        break;
                                    }
                                    case 2:
                                    {
                                        this.model[i].rotateAngleY = f3 * entry.getModelRota().value();
                                        break;
                                    }
                                    case 3:
                                    {
                                        this.model[i].rotateAngleZ = f3 * entry.getModelRota().value();
                                        break;
                                    }
                                }
                                break;
                            }
                        }
                        break;
                    }
                    case 5:
                    {
                        switch(entry.getModelRota().operation())
                        {
                            case 1:
                            {
                                switch(entry.getModelRota().axe())
                                {
                                    case 1:
                                    {
                                        this.model[i].rotateAngleX = f4 + entry.getModelRota().value();
                                        break;
                                    }
                                    case 2:
                                    {
                                        this.model[i].rotateAngleY = f4 + entry.getModelRota().value();
                                        break;
                                    }
                                    case 3:
                                    {
                                        this.model[i].rotateAngleZ = f4 + entry.getModelRota().value();
                                        break;
                                    }
                                }
                                break;
                            }
                            case 2:
                            {
                                switch(entry.getModelRota().axe())
                                {
                                    case 1:
                                    {
                                        this.model[i].rotateAngleX = f4 * entry.getModelRota().value();
                                        break;
                                    }
                                    case 2:
                                    {
                                        this.model[i].rotateAngleY = f4 * entry.getModelRota().value();
                                        break;
                                    }
                                    case 3:
                                    {
                                        this.model[i].rotateAngleZ = f4 * entry.getModelRota().value();
                                        break;
                                    }
                                }
                                break;
                            }
                        }
                        break;
                    }
                    case 6:
                    {
                        switch(entry.getModelRota().operation())
                        {
                            case 1:
                            {
                                switch(entry.getModelRota().axe())
                                {
                                    case 1:
                                    {
                                        this.model[i].rotateAngleX = f5 + entry.getModelRota().value();
                                        break;
                                    }
                                    case 2:
                                    {
                                        this.model[i].rotateAngleY = f5 + entry.getModelRota().value();
                                        break;
                                    }
                                    case 3:
                                    {
                                        this.model[i].rotateAngleZ = f5 + entry.getModelRota().value();
                                        break;
                                    }
                                }
                                break;
                            }
                            case 2:
                            {
                                switch(entry.getModelRota().axe())
                                {
                                    case 1:
                                    {
                                        this.model[i].rotateAngleX = f5 * entry.getModelRota().value();
                                        break;
                                    }
                                    case 2:
                                    {
                                        this.model[i].rotateAngleY = f5 * entry.getModelRota().value();
                                        break;
                                    }
                                    case 3:
                                    {
                                        this.model[i].rotateAngleZ = f5 * entry.getModelRota().value();
                                        break;
                                    }
                                }
                                break;
                            }
                        }
                        break;
                    }
                }
            }
        }
    }
}