package train.client.render.models.blocks;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.client.FMLClientHandler;
import net.minecraft.client.model.ModelBase;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;
import train.common.library.Info;
import train.common.tile.TileTCRail;

public class ModelLargeStraightTCTrack extends ModelBase {
	
	private IModelCustom modelLargeStraight;
	
	public ModelLargeStraightTCTrack() {
		modelLargeStraight = AdvancedModelLoader.loadModel(new ResourceLocation(Info.modelPrefix + "track_normal.obj"));
	}
	
	public void render() {
		modelLargeStraight.renderAll();
	}
	
	public void render(TileTCRail tcRail, double x, double y, double z) {
		// Push a blank matrix onto the stack
		GL11.glPushMatrix();
		
		// Move the object into the correct position on the block (because the OBJ's origin is the
		// center of the object)
		GL11.glTranslatef((float) x + 0.5f, (float) y, (float) z + 0.5f);

		// Bind the texture, so that OpenGL properly textures our block.
		FMLClientHandler.instance().getClient().renderEngine
				.bindTexture(new ResourceLocation(Info.resourceLocation, Info.modelTexPrefix + "track_normal.png"));
		GL11.glColor3f(1, 1, 1);
		// GL11.glScalef(0.5f, 0.5f, 0.5f);
		int facing = tcRail.getWorldObj().getBlockMetadata(tcRail.xCoord, tcRail.yCoord, tcRail.zCoord);
		
		if (facing == 3) {
			GL11.glRotatef(-90, 0, 1, 0);
		}
		if (facing == 1) {
			GL11.glRotatef(90, 0, 1, 0);
		}
		if (facing == 0) {
			GL11.glRotatef(180, 0, 1, 0);
		}
		
		for (int i = 0; i < 6; i++) {
			render();
			GL11.glTranslatef(0.0f, 0.0f, -1.0f);
		}
		
		// Pop this matrix from the stack.
		GL11.glPopMatrix();
	}
}