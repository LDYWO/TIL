const MAX_ROW = 20;
const MAX_COL = 12;
const INIT_INTERVAL = 500;

const TetrisBlockType = {"I_BLOCK" : 0,
                            "S_BLOCK" : 1,
                            "Z_BLOCK" : 2,
                            "O_BLOCK" : 3,
                            "L_BLOCK" : 4,
                            "J_BLOCK" : 5,
                            "T_BLOCK" : 6};

const BlockMovement = {"RIGHT" : 0,
                        "LEFT" : 1,
                        "DOWN" : 2};

const BlockRotate  = {"CLOCKWISE" : 0,
                       "COUNTERCLOCKWISE" : 1};

const KeyType = {"LEFT" : 188,
                  "RIGHT" : 191,
                  "DOWN" : 190,
                  "ROTATE_CLOCKWISE" : 88,
                  "ROTATE_COUNTERCLOCKWISE" : 90};

function SingleBlock() {
    this.blockFragmentType = null;
    this.permanent = false;

    this.init = function() {
        this.blockFragmentType = null;
        this.permanent = false;
    }
}

function Console() {
    this.initialized = true;
    this.pause = true;
    this.completedLines = 0;
    this.timer = null;
    this.currentBlock = null;
    this.nextBlock = null;
    this.score = 0;
    this.field = new Array();

    for (var i = 0; i < MAX_COL; i++) {
        this.field[i] = new Array(); // Array 안에 Array
        for (var j = 0; j < MAX_ROW; j++) {
            this.field[i][j] = new SingleBlock();
        }
    }

    drawTetrisField();

    this.paintCurrentBlock = function (flag) {
        if (this.currentBlock != null) {
            var originX = this.currentBlock.position.mx;
            var originY = this.currentBlock.position.my;

            for (var i = 0; i < 4; i++) {
                x = originX + this.currentBlock.relativeBlockPositions[i].mx;
                y = originY + this.currentBlock.relativeBlockPositions[i].my;

                var id = "c" + x + "r" + y;
                if (document.getElementById(id)) {
                    if (flag) {
                        var className = this.currentBlock.getBlockClassName();
                        document.getElementById(id).className = className();
                    }
                    else {
                        document.getElementById(id).className = "empty";
                    }
                }
            }
        }
    }

    this.paintNextBlock = function (flag) {
        if (this.nextBlock != null) {
            for (var i = 0; i < 4; i++) {
                var x = this.nextBlock.relativeBlockPositions[i].mx + 1;
                var y = this.nextBlock.relativeBlockPositions[i].my;
                var id = "nbc" + x + "r" + y;
                if (document.getElementById(id)) {
                    if (flag) {
                        var className = this.nextBlock.getBlockClassName();
                        document.getElementById(id).className = classname;
                    }
                    else {
                        document.getElementById(id).className = "empty";
                    }
                }
            }
        }
    }

    function drawTetrisField() {
        var str = "<table id = \"tetrisArea\">";
        for (var i = 0; i < MAX_ROW; i++) {
            str += "<td id = \"r" + i + "\"></td>";
            for (var j = 0; j < MAX_COL; j++) {
                str += "<td id = \"c" + j + "r" + i + "\"></td>";
            }
            str += "</tr>";
        }
        str += "<table>";
        document.getElementById('nextBlock').innerHTML = str;
    }

    this.getLevet = function () {
        return Math.floor(this.completedLines / 10) + 1;
    }

    this.init = function () {
        this.currentBlock = null;
        this.nextBlock = new TetrisBlock(Math.floor(Math.random() * 100) % 7);
        for (var i = 0; i < MAX_COL; i++) {
            for (var j = 0; j < MAX_ROW; j++) {
                this.field[i][j].init();
                var id = "c" + i + "r" + j
                document.getElementById(id).className = "empty";
                if (i < 4 && j < 2) {
                    var id = "nbc" + i + "r" + j;
                    document.getElementById(id).className = "empty";
                }
            }
        }
        this.initialized = true;
        this.score = 0;
    }

    this.begin = function () {
        this.stop();
        this.init();
        this.tick(this);
    }

    this.stop = function () {
        this.pause = true;
        clearTimeout(this.timer);
    }

    this.pauseNstart = function () {
        if (this.pause) {
            this.tick(this);
        }
        else {
            this.stop();
        }
    }

    this.tick = function () {
        this.pause = false;
        if (this.currentBlock == null) {
            this.currentBlock = this.nextBlock;
            this.paintNextBlock(false);
            this.nextBlock = new TetrisBlock(Math.floor(Math.random() * 100) % 7);
            this.paintNextBlock(true);
            this.paintCurrentBlock(true);
        }
        else {
            if (this.isPossibleMove(BlockMovement.DOWN)) {
                this.paintCurrentBlock(false);
                this.currentBlock.moveDown();
                this.paintCurrentBlock(true);
                this.score += 1;
                document.getElementById("score").innerHTML = this.score;
            }
            else {
                this.aggregateCurrentBlock();
            }
        }
        var self = this;
        if (!this.isOver()) {
            this.timer = setTimeout(function () {
                self.tick();
            }, self.getInterval(), self);
        }
        else {
            this.initialized = false;
        }
    }

    this.isOver = function () {
        if (this.currentBlock == null) {
            return false;
        }
        var relativeBlockPositions = this.currentBlock.relativeBlockPositions;
        var currentBlockPositions = this.currentBlock.position;

        var x, y;

        for (var i = 0; i < 4; i++) {
            x = currentBlockPositions.x + relativeBlockPositions[i].x;
            y = currentBlockPositions.y + relativeBlockPositions[i].y;
            if (this.isOccupied(x, y)) {
                return true;
            }
        }
        return false;
    }

    this.getInterval = function () {
        var newInterval = INIT_INTERVAL - (this.getLevel() - 1) * 50;
        return newInterval;
    }

    this.isOccupied = function (x, y) {
        if (x < 0 || x >= MAX_COL) {
            return true;
        }
        if (y < 0 || y >= MAX_ROW) {
            return true;
        }

        if (this.field[x][y].permanent) {
            return true;
        }
        else return false;
    }

    this.isPossibleMove = function (movement) {
        var xdiff, ydiff;

        switch (movement) {
            case BlockMovement.RIGHT:
                xdiff = 1;
                ydiff = 0;
                break;
            case BlockMovement.LEFT:
                xdiff = -1;
                ydiff = 0;
                break;
            case BlockMovement.DOWN:
                xdiff = 0;
                ydiff = 1;
                break;
        }

        var x, y;
        var currentBlock = this.currentBlock;

        for (var i = 0; i < 4; i++) {
            x = currentBlock.position.x + currentBlock.relativeBlockPositions[i].x;
            y = currentBlock.position.y + currentBlock.relativeBlockPositions[i].y;

            if (this.isOccupied(x + xdiff, y + ydiff)) {
                return false;
            }
        }
        return true;
    }

    this.isPossibleRotate = function (rotate) {
        var surroundingCheckList;
        var checkListElementCount;
        var currentBlock = this.currentBlock;

        switch (rotate) {
            case BlockRotate.CLOCKWISE:
                surroundingCheckList = currentBlock.rotateClockWiseCheckList;
                checkListElementCount = currentBlock.rotateClockWiseCheckList;
                break;
            case BlockRotate.COUNTERCLOCKWISE:
                surroundingCheckList = currentBlock.rotateCounterClockWiseCheckList;
                checkListElementCount = currentBlock.rotateCounterClockWiseCheckList;
                break;
        }
        var x, y;

        for (var i = 0; i < checkListElementCount; i++) {
            x = currentBlock.position.x + surroundingCheckList[i].x;
            y = currentBlock.position.y + surroundingCheckList[i].y;

            if (this.isOccupied(x, y)) {
                return false;
            }
        }
        return true;
    }

    this.moveBlockLeft = function () {
        if (this.currentBlock) {
            if (this.isPossibleMove(BlockMovement.LEFT)) {
                this.paintCurrentBlock(false);
                this.currentBlock.moveLeft();
                this.paintCurrentBlock(true);
            }
        }
    }

    this.moveBlockRight = function () {
        if (this.currentBlock) {
            if (gameConsole.isPossibleMove(BlockMovement.RIGHT)) {
                gameConsole.paintCurrentBlock(false);
                gameConsole.currentBlock.moveRight();
                gameConsole.paintCurrentBlock(true);
            }
        }
    }

    this.moveBlockDown = function () {
        if (this.currentBlock) {
            if (gameConsole.isPossibleMove(BlockMovement.DOWN)) {
                gameConsole.paintCurrentBlock(false);
                gameConsole.currentBlock.moveDown();
                gameConsole.paintCurrentBlock(true);
            }
        }
    }

    this.rotateBlockClockwise = function () {
        if (this.currentBlock) {
            if (gameConsole.isPossibleRotate(BlockRotate.CLOCKWISE)) {
                gameConsole.paintCurrentBlock(false);
                gameConsole.currentBlock.rotate(BlockRotate.CLOCKWISE);
                gameConsole.paintCurrentBlock(true);
            }
        }
    }

    this.rotateBlockCounterClockwise = function () {
        if (this.currentBlock) {
            if (gameConsole.isPossibleRotate(BlockRotate.COUNTERCLOCKWISE)) {
                gameConsole.paintCurrentBlock(false);
                gameConsole.currentBlock.rotate(BlockRotate.COUNTERCLOCKWISE);
                gameConsole.paintCurrentBlock(true);
            }
        }
    }

    this.aggregateCurrentBlock = function () {
        if (!this.currentBlock)
            return;

        var x, y;
        var _currentBlock = this.currentBlock;

        for (var i = 0; i < 4; i++) {
            x = _currentBlock.position.x + _currentBlock.relativeBlockPositions[i].x;
            y = _currentBlock.position.y + _currentBlock.relativeBlockPositions[i].y;
            this.field[x][y].permanent = true;
            this.field[x][y].blockFragmentType = _currentBlock.blockType;
        }
        var completeRows = this.checkCompletedRows();

        if (completeRows.length > 0) {
            this.completedLines += completeRows.length;
            this.truncateCompletedRows(completedRows);

            var scoreFactor = 0;

            for (var i = 0; i < completedRows.length; i++) {
                scoreFactor += i;
            }

            this.score += 10 * i;

            document.getElementById('lineCount').innerHTML = this.completedLines;
            document.getElementById('level').innerHTML = this.getLevel();
            document.getElementById('score').innerHTML = this.score;
        }
        this.score += 5;
        document.getElementById('score').innerHTML = this.score;
        this.currentBlock = null;
    }

    this.checkCompletedRows = function () {
        var affectedRows = new Array();

        for (var i = 0; i < 4; i++) {
            var found = false;
            var toFind = this.currentBlock.position.y + this.currentBlock.relativeBlockPositions[i].y;

            for (var j = 0; j < affectedRows.length; j++) {
                if (affectedRows[j] == toFind) {
                    found = true;
                    break;
                }
            }
            if (!found) {
                affectedRows.push(toFind);
            }
        }
        var completedRows = new Array();

        for (var i = 0; i < affectedRows.length; i++) {
            var isNotComplete = false;
            for (var j = 0; j < MAX_COL; j++) {
                if (!this.field[j][affectedRows[i]].permanent) {
                    isNotComplete = true;
                    break;
                }
            }
            if (!isNotComplete) {
                completedRows.push(affectedRows[i]);
            }
        }
        return completedRows.sort(sortNumber);
    }

    this.truncateCompletedRows = function (completedRows) {
        for (var i = 0; i < completedRows.length; i++) {
            for (var row = completedRows[i]; row >= 0; row--) {
                var tops = true;

            }
        }
    }

}

var Person = function (arg) {
    var name = arg? arg : "zzoon";

    return {
        getName: function() {
            return name;
        },
        setName: function (value) {
            name = value;
        }
    };
};

var me = new Person();
console.log(me.getName());
