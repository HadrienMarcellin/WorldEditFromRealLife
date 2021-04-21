package com.sk89q.worldedit.command;


import com.sk89q.worldedit.EditSession;
import com.sk89q.worldedit.LocalSession;
import com.sk89q.worldedit.WorldEdit;
import com.sk89q.worldedit.WorldEditException;
import com.sk89q.worldedit.command.util.CommandPermissions;
import com.sk89q.worldedit.command.util.CommandPermissionsConditionGenerator;
import com.sk89q.worldedit.command.util.Logging;
import com.sk89q.worldedit.entity.Player;
import com.sk89q.worldedit.extension.platform.Actor;
import com.sk89q.worldedit.function.pattern.Pattern;
import com.sk89q.worldedit.internal.annotation.Radii;
import com.sk89q.worldedit.math.BlockVector3;
import com.sk89q.worldedit.session.SessionManager;
import com.sk89q.worldedit.util.formatting.text.TextComponent;
import com.sk89q.worldedit.util.formatting.text.TranslatableComponent;
import org.enginehub.piston.annotation.Command;
import org.enginehub.piston.annotation.CommandContainer;
import org.enginehub.piston.annotation.param.Arg;
import org.enginehub.piston.annotation.param.Switch;

import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

@CommandContainer(superTypes = CommandPermissionsConditionGenerator.Registration.class)
public class AutomatedMappingCommands {

    private final WorldEdit worldEdit;

    /**
     * Create a new instance
     *
     * @param worldEdit : reference to word edit
     */
    public AutomatedMappingCommands(WorldEdit worldEdit) {
        checkNotNull(worldEdit);
        this.worldEdit = worldEdit;
    }


    @Command(
            name = "/hadrien_test",
            desc = "Creates a simple pattern to test how to generate blocks with WordEdit."
    )
    @CommandPermissions("worldedit.automated_generation.hadrien_test")
    @Logging(Logging.LogMode.PLACEMENT)
    public int hadrien_test(Player player, LocalSession session, EditSession editSession,
                            @Arg(desc = "The pattern of blocks to generate")
                                    Pattern pattern
                            ) throws WorldEditException {

        BlockVector3 pos = session.getPlacementPosition(player);
        double player_yaw = player.getLocation().getYaw();
        int affected = editSession.makeHadrienTest(pos, player_yaw, pattern);
        player.printInfo(TranslatableComponent.of("worldedit.hadrien_test.created", TextComponent.of(affected)));
        return affected;

    }

}
