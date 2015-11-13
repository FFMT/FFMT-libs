package fr.minecraftforgefrance.ffmtlibs.entity;

import java.util.List;

import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.item.EntityFireworkRocket;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;
import net.minecraftforge.fml.common.registry.EntityRegistry;

public class EntityHelper
{

    /**
     * Spawn smoke particles(ENTITY ONLY)
     * @param speed the speed of the particle
     * @param entity instance of the entity
     * @param velX velocity x of the particle
     * @param velY velocity y of the particle
     * @param velZ velocity z of the particle
     */
    public static void spawnSmokeParticles(int speed, EntityLiving entity, double velX, double velY, double velZ)
    {
        spawnSmokeParticles(speed, entity, velX, velY, velZ, 0, 0, 0, new int[0]);
    }

    /**
     * Spawn smoke particles(ENTITY ONLY)
     * @param speed the speed of the particle
     * @param entity instance of the entity
     * @param xVel velocity x of the particle
     * @param yVel velocity y of the particle
     * @param zVel velocity z of the particle
     * @param xPosition x
     * @param yPosition y
     * @param zPosition z
     * @param something i don't know what is it
     */
    public static void spawnSmokeParticles(int speed, EntityLiving entity, double xVel, double yVel, double zVel, double xPosition, double yPosition, double zPosition, int... something)
    {
        for(int i = 0; i < speed; i++)
        {
            entity.worldObj.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, entity.posX + xPosition, entity.posY + yPosition, entity.posZ + zPosition, xVel, yVel, zVel, something);
        }
    }

    /**
     * Add a mob easier
     * @param entityClass The entity class
     * @param entityName The entity name
     * @param id The entity ID
     * @param mod instance of your mod
     * @param trackingRange Number of tracking range
     * @param updateFrequency Number update frequency
     * @param sendsVelocityUpdates Send velocity updates or not
     * @param backGroundEggColour Background egg color
     * @param foreGroundEggColour Foreground egg color
     * @param weightedProb Chance to spawn
     * @param minSpawn Minimum of entity per spawn
     * @param maxSpawn Maximum of entity per spawn
     * @param creatureType type of the entity
     */
    public static void addMob(Class<? extends Entity> entityClass, String entityName, int id, Object mod, int trackingRange, int updateFrequency, boolean sendsVelocityUpdates, int backGroundEggColour, int foreGroundEggColour, int weightedProb, int minSpawn, int maxSpawn, EnumCreatureType creatureType)
    {
        EntityRegistry.registerModEntity(entityClass, entityName, id, mod, trackingRange, updateFrequency, sendsVelocityUpdates);
        EntityRegistry.registerEgg(entityClass, backGroundEggColour, foreGroundEggColour);
        EntityRegistry.addSpawn(entityName, weightedProb, minSpawn, maxSpawn, creatureType);
    }

    /**
     * Add a mob easier
     * @param entityClass The entity class
     * @param entityName The entity name
     * @param id The entity ID
     * @param mod instance of your mod
     * @param trackingRange Number of tracking range
     * @param updateFrequency Number update frequency
     * @param sendsVelocityUpdates Send velocity updates or not
     * @param backGroundEggColour Background egg color
     * @param foreGroundEggColour Foreground egg color
     * @param weightedProb Chance to spawn
     * @param minSpawn Minimum of entity per spawn
     * @param maxSpawn Maximum of entity per spawn
     * @param creatureType type of the entity
     * @param biome Biome where you want to spawn the mob)(If not specified, this mob doesn't spawn naturally)
     */
    public static void addMob(Class<? extends Entity> entityClass, String entityName, int id, Object mod, int trackingRange, int updateFrequency, boolean sendsVelocityUpdates, int backGroundEggColour, int foreGroundEggColour, int weightedProb, int minSpawn, int maxSpawn, EnumCreatureType creatureType, BiomeGenBase... biome)
    {
        EntityRegistry.registerModEntity(entityClass, entityName, id, mod, trackingRange, updateFrequency, sendsVelocityUpdates);
        EntityRegistry.registerEgg(entityClass, backGroundEggColour, foreGroundEggColour);
        EntityRegistry.addSpawn(entityName, weightedProb, minSpawn, maxSpawn, creatureType, biome);
    }

    /**
     * Set custom attack target ! (PUT THIS FUNCTION IN
     * updateEntityActionState() METHOD !!!!)
     * @param entityHostAttack The entity host the attack(like a entity instance)
     * @param classToAttack The entity to attack
     * @author elias54
     **/
    public static void targetEntity(EntityCreature entityHostAttack, Class<? extends EntityLivingBase> classToAttack)
    {
        List list = entityHostAttack.worldObj.getEntitiesWithinAABB(classToAttack, AxisAlignedBB.fromBounds(entityHostAttack.posX, entityHostAttack.posY, entityHostAttack.posZ, entityHostAttack.posX + 1, entityHostAttack.posY + 1, entityHostAttack.posZ + 1).expand(16D, 4D, 16D));
        for(int i = 0; i < list.size(); i++)
        {
            EntityLivingBase entityToAttack = (EntityLivingBase)list.get(entityHostAttack.worldObj.rand.nextInt(i));
            if(!list.isEmpty())
            {
                entityHostAttack.setAttackTarget(entityToAttack);
            }
        }
    }

    /**
     * Throw entity problem exception
     * @param message Your message exception
     * @param throwable The instance of the Throwable
     * @author elias54
     */
    public static void throwEntityException(String message, Throwable throwable)
    {
        ObfuscationReflectionHelper.setPrivateValue(java.lang.Throwable.class, throwable, message, "detailMessage");
        FMLCommonHandler.instance().raiseException(throwable, message, true);
    }

    /**
     * Remove current listed loaded entity
     * @param world the instance of the world
     * @param entityToRemove the entity to remove
     * @author elias54
     */
    public static void removeLoadedEntityList(World world, Entity entityToRemove)
    {
        List list = world.getLoadedEntityList();
        if(!list.isEmpty())
        {
            list.remove(entityToRemove);
        }
    }

    /**
     * Set infinite health to specified entity
     * @param entity entity to set infinite health
     * @author elias54
     */
    public static void setInfiniteHealth(EntityLivingBase entity)
    {
        entity.setHealth(Float.POSITIVE_INFINITY);
    }

    /**
     * Spawn a firework with specified values
     * @param world
     * @param x coord x
     * @param y coord y
     * @param z coord z
     * @param type
     *        0 = normal, 1 = large ball, 2 = star shaped, 3 = creeper face,
     *        4 = burst
     * @param flicker
     * @param trail
     * @param colorAmount number of colors
     * @param colors new int[colorAmount], with all different colors
     * @param flight time before explode 0 = short, 1 = medium, 2 = long
     */
    public static void spawnFireWorks(World world, double x, double y, double z, byte type, boolean flicker, boolean trail, int colorAmount, int[] colors, byte flight)
    {
        ItemStack firework = new ItemStack(Items.fireworks);

        NBTTagCompound mainTag = new NBTTagCompound();
        NBTTagCompound fireworksTag = new NBTTagCompound();
        NBTTagCompound explosionTag = new NBTTagCompound();
        NBTTagList explosionList = new NBTTagList();

        explosionTag.setByte("Type", type);
        explosionTag.setBoolean("Flicker", flicker);
        explosionTag.setBoolean("Trail", trail);
        for(int a = 0; a < colorAmount; a++)
        {
            colors[a] = 255 * a;
        }
        explosionTag.setIntArray("Colors", colors);

        explosionList.appendTag(explosionTag);

        fireworksTag.setTag("Explosions", explosionList);
        fireworksTag.setByte("Flight", flight);

        mainTag.setTag("Fireworks", fireworksTag);

        firework.setTagCompound(mainTag);

        EntityFireworkRocket rocket = new EntityFireworkRocket(world, x, y, z, firework);
        world.spawnEntityInWorld(rocket);
    }
}